import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import Button from "../../../common/components/button/Button";
import TextArea from "../../../common/components/input/TextArea";
import { useEffect, useState } from "react";
import { createComment, readPostDetail, updatePost, type CommentCreateDto, type PostUpdateDto } from "../features/PostClick";
import CommentList from "../list/PostCommentList";
import { getCurrentUserId } from "../../utils/auth";

interface PostDetailModalProps {
  postId: number;
  authorId: number;
  nicknameOrName: string;
  onClose: () => void;
}


interface CommentResponseDto {
  authorId: number;
  nicknameOrName: string;
  content: string;
  createdDate: string;
}
function PostDetailModal({ postId, onClose }: PostDetailModalProps) {
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [comment, setComment] = useState<string>("");
  const [comments, setComments] = useState<CommentResponseDto[]>([]);
  const [authorId, setAuthorId] = useState<number | null>(null);
  const [currentUserId, setCurrentUserId] = useState<number | null>(null);

  const fetchPostDetail = async () => {
    try {
      const res = await readPostDetail(postId);
      console.log(res);
      const { title, content, author_id, comments } = res.data;
      setTitle(title);
      setContent(content);
      setAuthorId(author_id);
      setComments(comments);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    const userId = getCurrentUserId();
    setCurrentUserId(userId);
    fetchPostDetail();
  }, [postId]);

  const handleCommentWrite = async () => {
    try {
      const dto: CommentCreateDto = {
        postId,
        content: comment,
      };
      await createComment(dto);
      setComment("");
      await fetchPostDetail();
    } catch (err) {
      console.error(err);
    }
  };

  const handlePostUpdate = async () => {
    try {
      const dto: PostUpdateDto = {
        id: postId,
        title: title,
        content: content
      };
      const res = await updatePost(dto);
      setTitle(res.data);
      setContent(res.data)
    } catch(err){
      console.error(err)
    }
  }

  const isAuthor = currentUserId !== null && currentUserId === authorId;

  const handleCommentAuthorClick = function (authorId: number, nicknameOrName: string): void {
  console.log("작성자 ID: " , authorId);
  console.log("작성자 이름: " ,nicknameOrName);

};

  return (
    <CommonModal size="l" onClose={onClose}>
      <div className="font-roboto">
        <ModalTitle title={"게시글"} />
        <div className="flex-1 space-y-2">
          <TextArea size={"l"} label={"제목"} placeholder={""} disabled={!isAuthor} value={title} setValue={setTitle} />
          <TextArea size={"l"} label={"내용"} placeholder={""} disabled={!isAuthor} value={content} setValue={setContent} />
          <TextArea size={"l"} label={"댓글 작성"} placeholder={"댓글을 입력하세요"} disabled={false} value={comment} setValue={setComment} />
        </div>

        <div className="flex justify-end mb-10 mr-6">
          <Button color={"theme"} text={"댓글작성"} size={"s"} disabled={false} type="button" onClick={handleCommentWrite} />
        </div>

        <div className="mb-6">
          <CommentList comments={comments} onAuthorClick = {handleCommentAuthorClick}/>
        </div>

        {isAuthor && (
          <div className="flex justify-end mt-4 mr-6">
            <Button color={"theme"} size={"s"} disabled={!isAuthor} text={"수정"} type="button" onClick = {handlePostUpdate}/>
          </div>
        )}
      </div>
    </CommonModal>
  );
}

export default PostDetailModal;
