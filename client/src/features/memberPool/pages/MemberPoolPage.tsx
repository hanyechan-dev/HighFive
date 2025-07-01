import { useState, useEffect } from 'react';
import Button from '../../../common/components/button/Button';
import Pagination from '../../../common/components/pagination/Pagination';
import CommonPage from '../../../common/pages/CommonPage';
import MemberPoolSummaryRow from '../components/MemberPoolSummaryRow';
import MemberPoolDetailModal from '../modals/MemberPoolDetailModal';
import MemberPoolFilterModal from '../modals/MemberPoolFilterModal';
import { MemberPoolPageApi } from '../apis/MemberPoolApi';
import type { MemberPoolSummary } from '../props/MemberPoolProps';
import { useSelector } from 'react-redux';
import type { RootState } from '../../../common/store/store';

import { usePagination } from '../../../common/customHooks/usePagination';
import MemberPoolListHeader from '../components/MemberPoolListHeader';
import { printErrorInfo } from '../../../common/utils/ErrorUtil';
import PageTitle from '../../../common/components/title/PageTitle';
import LoadingSpinner from '../../../common/components/loading/LoadingSpinner';
import EmptyState from '../../../common/components/emptyState/EmptyState';

const MemberPoolPage = () => {
  const filter = useSelector((state: RootState) => state.memberPoolFilter.filter);

  const [members, setMembers] = useState<MemberPoolSummary[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const [totalElements, setTotalElements] = useState(0);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
  const [isFilterModalOpen, setIsFilterModalOpen] = useState(false);

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
      setIsLoading(true);
      try {
        // 모든 페이지에서 10개씩 요청
        const res = await MemberPoolPageApi(filter, clickedPage, 10);
        const content = res?.data?.content || [];

        // 모든 페이지에서 리스트만 표시
        setMembers(content);

        setTotalElements(res?.data?.totalElements || 0);
      } catch (err) {
        printErrorInfo(err);
        setMembers([]);
        setTotalElements(0);
      } finally {
        setIsLoading(false);
      }
    };
    fetchMembers();
  }, [clickedPage, filter]);

  const handleMemberClick = (id: number) => {
    setIsDetailModalOpen(true);
    setSelectedId(id);
  };

  if (isLoading) {
    return (
      <CommonPage>
        <div className="w-[1452px] mx-auto px-0 font-roboto">
          <div className="flex items-center justify-between mb-8">
            <PageTitle
              title="인재풀페이지"
              description="AI 기반으로 추천된 인재들을 확인해보세요"
            />
          </div>
          <LoadingSpinner message="인재 정보를 불러오는 중..." size="lg" color="theme" />
        </div>
      </CommonPage>
    );
  }

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto px-0 font-roboto">
        {/* 헤더 섹션 */}
        <div className="flex items-center justify-between mb-8">
          <PageTitle title="인재풀페이지" description="AI 기반으로 추천된 인재들을 확인해보세요" />
        </div>

        {members.length === 0 ? (
          <EmptyState
            title="검색된 인재가 없습니다"
            text="다른 조건으로 검색하거나 필터를 조정해보세요"
          />
        ) : (
          <>
            {/* 인재 리스트 섹션 */}
            <div>
              <div className="flex items-center justify-between mb-6">
                <div className="flex items-center gap-3">
                  <h2 className="text-xl font-semibold">인재 목록</h2>
                  <div className="h-px flex-1 bg-gradient-to-r from-gray-200 to-transparent"></div>
                </div>
                <Button
                  color="theme"
                  size="s"
                  disabled={false}
                  text="필터"
                  type="button"
                  onClick={() => setIsFilterModalOpen(true)}
                />
              </div>
              {/* 헤더 */}
              <MemberPoolListHeader />
              {/* 리스트 */}
              <div>
                {members.map((member) => (
                  <MemberPoolSummaryRow
                    key={member.id}
                    member={member}
                    onClick={handleMemberClick}
                  />
                ))}
              </div>
            </div>
            {/* 페이지네이션 */}
            <div className="mt-8 flex justify-center">
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
            </div>
          </>
        )}
      </div>
      {selectedId && (
        <MemberPoolDetailModal
          isOpen={isDetailModalOpen}
          onClose={() => setIsDetailModalOpen(false)}
          memberId={selectedId}
        />
      )}
      <MemberPoolFilterModal
        isOpen={isFilterModalOpen}
        onClose={() => setIsFilterModalOpen(false)}
      />
    </CommonPage>
  );
};

export default MemberPoolPage;
