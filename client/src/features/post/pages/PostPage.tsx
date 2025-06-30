import { useEffect, useState } from "react";
import Button from "../../../common/components/button/Button";
import PostWrite from "../modals/PostWriteModal";
import PostDetailModal from "../modals/PostDetailModal";
import CommonPage from "../../../common/pages/CommonPage";
import Pagination from "../../../common/components/pagination/Pagination";
import { readPostPage } from "../apis/PostApi";
import { usePagination } from "../../../common/customHooks/usePagination";
import PostList from "../components/PostList";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import PageTitle from "../../../common/components/title/PageTitle";

interface PostSummaryDto {
    id: number;
    authorId: number;
    title: string;
    nicknameOrName: string;
    commentCount: number;
    createdDate: string;
}

function PostPage() {
    const [isWriteOpen, setIsWriteOpen] = useState(false);
    const [isDetailOpen, setIsDetailOpen] = useState(false);
    const [selectedPostId, setSelectedPostId] = useState<number>(-1);
    const [posts, setPosts] = useState<PostSummaryDto[]>([]);
    const [totalElements, setTotalElements] = useState(0);
    const [selectedAuthorId, setSelectedAuthorId] = useState<number>(-1);
    const [selectedAuthorName, setSelectedAuthorName] = useState<string>("");

    const elementsPerPage = 10;
    const pagesPerBlock = 10;

    const {
        clickedPage,
        pageBlockIndex,
        lastPage,
        lastPageBlockIndex,
        onClickFirst,
        onClickPrev,
        onClickNext,
        onClickLast,
        setClickedPage,
    } = usePagination({
        totalElements,
        elementsPerPage,
        pagesPerBlock,
    });

    const openWriteModal = () => setIsWriteOpen(true);

    const closeWriteModal = () => {
        setIsWriteOpen(false);
        fetchPosts();
    };

    const handlePostClick = (id: number, authorId: number, nicknameOrName: string) => {
        console.log("게시글 ID:", id);
        console.log("작성자 ID:", authorId);
        console.log("작성자 닉네임/이름:", nicknameOrName);
        setSelectedPostId(id);
        setSelectedAuthorId(authorId);
        setSelectedAuthorName(nicknameOrName);
        setIsDetailOpen(true);
    };

    const closeDetailModal = () => {
        setIsDetailOpen(false);
        setSelectedPostId(-1);
    };

    const fetchPosts = async () => {
        try {
            const res = await readPostPage(clickedPage - 1, elementsPerPage);
            setPosts(res.data.content);
            setTotalElements(res.data.totalElements);
        } catch (error) {
            console.error("게시글 불러오기 실패", error);
        }
    };

    useEffect(() => {
        fetchPosts();
    }, [clickedPage]);


    const handleUpdateCommentCount = (postId: number) => {
        setPosts((prevPosts) =>
            prevPosts.map((post) =>
                post.id === postId
                    ? { ...post, commentCount: post.commentCount + 1 }
                    : post
            )
        );
    };


    return (
        <>
            <CommonPage>
                <PageTitle title={"커뮤니티"} />

                <div className="flex justify-end items-center mt-6 mr-6">
                    <Button
                        text="+ 작성"
                        color="theme"
                        size="s"
                        type="button"
                        onClick={openWriteModal}
                        disabled={false}
                    />
                </div>

                <div className="space-y-4">
                    {posts.length > 0 ? (posts.map((post) => (
                        <PostList
                            key={post.id}
                            title={post.title}
                            nicknameOrName={post.nicknameOrName}
                            commentCount={post.commentCount}
                            createdDate={post.createdDate}
                            onClick={() => handlePostClick(post.id, post.authorId, post.nicknameOrName)}
                        />
                    ))):
                <EmptyState title={"등록된 게시글이 없습니다."} text={"게시글을 등록해보세요."} />}
                </div>

                <div className="flex justify-center">
                    <Pagination
                        currentPageBlockIndex={pageBlockIndex}
                        lastPageBlockIndex={lastPageBlockIndex}
                        pagesPerBlock={pagesPerBlock}
                        lastPage={lastPage}
                        clickedPage={clickedPage}
                        onClickFirst={onClickFirst}
                        onClickPrev={onClickPrev}
                        onClickNext={onClickNext}
                        onClickLast={onClickLast}
                        onClickPage={setClickedPage}

                    />
                </div>
            </CommonPage>

            {isWriteOpen && <PostWrite onClose={closeWriteModal} />}
            {isDetailOpen && selectedPostId !== null && (
                <PostDetailModal
                    postId={selectedPostId}
                    authorId={selectedAuthorId}
                    nicknameOrName={selectedAuthorName}
                    onClose={closeDetailModal}
                    onUpdateCommentCount={handleUpdateCommentCount}
                />
            )
            }
        </>
    );
}

export default PostPage;
