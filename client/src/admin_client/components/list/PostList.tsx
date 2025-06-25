import { useState } from "react";

interface PostListProps {
  nicknameOrName: string;
  title: string;
  commentCount: number;
  createdDate: string;
  onClick: () => void;
}

const PostList = ({
  title,
  nicknameOrName,
  commentCount,
  createdDate,
  onClick,
}: PostListProps) => {
  const formattedDate = new Date(createdDate).toLocaleString("ko-KR");

  return (
    <div
      className="w-[1400px] rounded-lg border px-4 py-3 mb-3 flex justify-between items-center hover:bg-semi_theme cursor-pointer text-base font-roboto"
      onClick={onClick}
    >
      <div>
        <h4 className="text-base font-semibold">{title}</h4>
        <p className="text-sm text-gray-500">{nicknameOrName}</p>
      </div>
      <div className="flex items-center gap-4 text-sm text-gray-500">
        <span> 댓글 ({commentCount})</span>
        <span>{formattedDate}</span>
      </div>
    </div>
  );
};

export default PostList;
