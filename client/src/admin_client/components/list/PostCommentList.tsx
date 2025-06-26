
interface CommentResponseDto {
  authorId: number;
  nicknameOrName: string;
  content: string;
  createdDate: string;
}

// props 타입
interface CommentListProps {
  comments: CommentResponseDto[];
  onAuthorClick?: (authorId: number, nicknameOrName: string) => void;
}

// 댓글 리스트 렌더링
function CommentList({ comments, onAuthorClick }: CommentListProps) {
  if (comments.length === 0) {
    return <p className="text-sm text-gray-400">아직 작성된 댓글이 없습니다.</p>;
  }

  return (
    <ul className="ml-6 w-[952px] space-y-3">
      {comments.map((comment, index) => (
        <li key={index} className="p-3 border rounded-xl bg-[#FAFAFA]">
          <div
            className="text-sm font-semibold text-theme cursor-pointer hover:underline w-fit"
            onClick={() =>
              onAuthorClick?.(comment.authorId, comment.nicknameOrName)
            }
          >
            {comment.nicknameOrName}
          </div>
          <div className="flex justify-center gap-[635px]">
            <div className="text-sm text-gray-800 mt-1">{comment.content}</div>
            <div className="text-xs text-gray-400 mt-1">
              {new Date(comment.createdDate).toLocaleString("ko-KR")}
            </div>
          </div>
        </li>
      ))}
    </ul>

)
}

export default CommentList;
