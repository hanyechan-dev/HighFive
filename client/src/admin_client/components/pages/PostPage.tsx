import { useState } from "react";
import Button from "../../../common/components/button/Button";
import PostList from "../list/PostList";
import SearchIcon from "../../../common/icons/searchIcon";
import PostWrite from "../modals/PostWriteModal";
import CommonPage from "../../../common/pages/CommonPage";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/coustomHooks/usePagination";

interface Post {
  id: number;
  nicknameOrName: string;
  title: string;
  commentCount: number;
  createdDate: string;
}

const dummyPosts: Post[] = [
  { id: 1, title: "게시글 제목1", nicknameOrName: "작성자1", commentCount: 3, createdDate: "2025-06-20" },
  { id: 2, title: "게시글 제목2", nicknameOrName: "작성자2", commentCount: 0, createdDate: "2025-06-19" },
  { id: 3, title: "게시글 제목3", nicknameOrName: "작성자3", commentCount: 5, createdDate: "2025-06-18" },
  { id: 4, title: "게시글 제목4", nicknameOrName: "작성자4", commentCount: 2, createdDate: "2025-06-17" },
  { id: 5, title: "게시글 제목5", nicknameOrName: "작성자5", commentCount: 1, createdDate: "2025-06-16" },
  { id: 6, title: "게시글 제목6", nicknameOrName: "작성자6", commentCount: 4, createdDate: "2025-06-15" },
  { id: 7, title: "게시글 제목7", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 8, title: "게시글 제목8", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 9, title: "게시글 제목9", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 10, title: "게시글 제목10", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 11, title: "게시글 제목11", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 12, title: "게시글 제목12", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 13, title: "게시글 제목13", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
  { id: 14, title: "게시글 제목14", nicknameOrName: "작성자7", commentCount: 7, createdDate: "2025-06-14" },
];

function PostPage() {
  const [search, setSearch] = useState("");
  const [isWriteOpen, setIsWriteOpen] = useState(false);

  const openWriteModal = () => setIsWriteOpen(true);
  const closeWriteModal = () => setIsWriteOpen(false);

  const elementsPerPage = 10;
  const pagesPerBlock = 10;

  const filteredPosts = dummyPosts.filter((post) =>
    post.title.includes(search)
  );
  const totalElements = filteredPosts.length;

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
    totalElements: totalElements,
    elementsPerPage: elementsPerPage,
    pagesPerBlock: pagesPerBlock,
  });

  const startIdx = (clickedPage - 1) * elementsPerPage;
  const endIdx = startIdx + elementsPerPage;
  const paginatedPosts = filteredPosts.slice(startIdx, endIdx);

  return (
    <CommonPage>
      <div className="p-6 mx-auto bg-white min-h-screen font-roboto">
        <span className="text-2xl font-roboto mt-[24px] relative top-[4px] float-left">
          커뮤니티
        </span>

        <div className="flex justify-end items-center mt-6 ml-1 mb-4">
          <div className="relative w-[300px] mb-6">
            <SearchIcon
              className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"
              width={16}
              height={16}
            />
            <input
              type="text"
              placeholder="검색"
              className="w-full pl-10 pr-4 py-2 border rounded-lg text-sm"
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>

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
          {paginatedPosts.map((post) => (
            <PostList
              key={post.id}
              title={post.title}
              nicknameOrName={post.nicknameOrName}
              commentCount={post.commentCount}
              createdDate={post.createdDate}
            />
          ))}
        </div>

        {isWriteOpen && <PostWrite onClose={closeWriteModal} />}
      </div>

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
    </CommonPage>
  );
}

export default PostPage;
