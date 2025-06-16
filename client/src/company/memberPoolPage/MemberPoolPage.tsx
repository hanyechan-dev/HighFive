import { useEffect, useState } from "react";
import CommonPage from "../../common/pages/CommonPage";
import MemberPoolDetailModal from "./MemberPoolDetailModal";
import { MemberPoolPageApi, type MemberPoolPage } from "./MemberPoolApi";
import Pagination from "../../common/components/pagination/Pagination";
import { usePagination } from "../../common/coustomHooks/usePagination";
import MemberPoolFilterModal from "./MemberPoolFilterModal";

export default function MemberPoolPage() {
  const [members, setMembers] = useState<MemberPoolPage[]>([]);
  const [totalElements, setTotalElements] = useState(0);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [isDetailOpen, setDetailOpen] = useState(false);
  const [isFilterOpen, setFilterOpen] = useState(false);

  const {
    clickedPage,
    setClickedPage,
    lastPage,
    pageBlockIndex,
    lastPageBlockIndex,
    onClickFirst,
    onClickPrev,
    onClickNext,
    onClickLast,
  } = usePagination({
    totalElements,
    elementsPerPage: 10,
    pagesPerBlock: 10,
  });

  useEffect(() => {
    const fetchMembers = async () => {
      try {
        const res = await MemberPoolPageApi({}, clickedPage);
        setMembers(res.data.content);
        setTotalElements(res.data.totalElements);
      } catch (err) {
        console.error(err);
      }
    };
    fetchMembers();
  }, [clickedPage]);

  // AI 추천 mock 데이터 예시
  const aiRecommended = [
    { id: 1, name: "김인재", job: "프론트엔드 개발자", career: "3년", similarity: 95 },
    { id: 2, name: "이개발", job: "백엔드 개발자", career: "5년", similarity: 92 },
    { id: 3, name: "박디자인", job: "UI/UX 디자이너", career: "2년", similarity: 88 },
    { id: 4, name: "최기획", job: "프로덕트 매니저", career: "7년", similarity: 85 },
  ];

  return (
    <CommonPage>
      <div className="font-bold text-2xl mb-6">인재풀</div>
      <div className="flex justify-end mb-6">
        <button
          className="border border-theme text-theme rounded px-4 py-2"
          onClick={() => setFilterOpen(true)}
        >
          필터
        </button>
      </div>

      {/* AI 인재 추천 */}
      <div className="mb-10">
        <div className="font-bold text-lg mb-3">AI 인재 추천</div>
        <div className="flex gap-4">
          {aiRecommended.map(r => (
            <div key={r.id} className="border rounded-lg p-4 min-w-[220px]">
              <div className="font-semibold">{r.name}</div>
              <div className="text-sm text-gray-500">{r.job}</div>
              <div className="text-sm text-gray-500">경력: {r.career}</div>
              <div className="text-pink-400 text-xs font-bold mt-2">유사도 {r.similarity}%</div>
            </div>
          ))}
        </div>
      </div>

      {/* 인재 리스트 */}
      <div>
        <div className="font-bold text-lg mb-3">인재 리스트</div>
        <table className="w-full border">
          <thead>
            <tr className="bg-gray-50">
              <th className="py-2">이름</th>
              <th>성별</th>
              <th>나이</th>
              <th>경력</th>
              <th>직무</th>
              <th>학력</th>
            </tr>
          </thead>
          <tbody>
            {members.map(member => (
              <tr
                key={member.id}
                className="hover:bg-gray-100 cursor-pointer"
                onClick={() => {
                  setSelectedId(member.id);
                  setDetailOpen(true);
                }}
              >
                <td>{member.name}</td>
                <td>{member.gender}</td>
                <td>{member.age}세</td>
                <td>{member.careerYear}년</td>
                <td>{member.job}</td>
                <td>{member.educationLevel}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Pagination
        currentPageBlockIndex={pageBlockIndex}
        lastPageBlockIndex={lastPageBlockIndex}
        pagesPerBlock={10}
        lastPage={lastPage}
        clickedPage={clickedPage}
        onClickFirst={onClickFirst}
        onClickPrev={onClickPrev}
        onClickNext={onClickNext}
        onClickLast={onClickLast}
        onClickPage={setClickedPage}
      />

      <MemberPoolDetailModal
        isOpen={isDetailOpen}
        onClose={() => setDetailOpen(false)}
        memberId={selectedId}
      />

      <MemberPoolFilterModal
        isOpen={isFilterOpen}
        onClose={() => setFilterOpen(false)}
      />
    </CommonPage>
  );
}
