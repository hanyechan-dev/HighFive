import { useEffect, useState } from "react";
import { usePagination } from "../../../common/customHooks/usePagination";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { ProposalSummary } from "../props/ProposalProps";
import { ProposalListApi } from "../apis/ProposalApi";
import CommonPage from "../../../common/pages/CommonPage";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import ProposalListHeader from "../components/ProposalListHeader";
import ProposalSummaryRow from "../components/ProposalSummaryRow";
import Pagination from "../../../common/components/pagination/Pagination";
import ProposalDetailModal from "../modals/ProposalDetailModal";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import PageTitle from "../../../common/components/title/PageTitle";

const ProposalPage = () => {
  const [proposals, setProposals] = useState<ProposalSummary[]>([]);
  const [totalElements, setTotalElements] = useState(0);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [isDetailOpen, setDetailOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

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
    const fetchProposals = async () => {
      setIsLoading(true);
      try {
        const res = await ProposalListApi(clickedPage - 1, 10);
        if (res && res.data.content) {
          setProposals(res.data.content);
          setTotalElements(res.data.totalElements);
        }
      } catch (err) {
        printErrorInfo(err);
      } finally {
        setIsLoading(false);
      }
    };
    fetchProposals();
  }, [clickedPage]);

  const handleProposalClick = (id: number) => {
    setSelectedId(id);
    setDetailOpen(true);
  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="제안서 현황 페이지"
            description="보낸 제안서들을 확인하고 관리하세요"
          />
        </div>
        
        {isLoading ? (
          <LoadingSpinner message="제안 내역을 불러오는 중..." />
        ) : (
          <>
            <ProposalListHeader />
            {proposals.length === 0 ? (
              <EmptyState
                title="제안 내역이 없습니다."
                text="아직 제안한 내역이 없습니다."
              />
            ) : (
              proposals.map((proposal) => (
                <ProposalSummaryRow
                  key={proposal.id}
                  proposal={proposal}
                  onClick={handleProposalClick}
                />
              ))
            )}
          </>
        )}
        
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

        {/* 상세 모달 */}
        {selectedId && (
          <ProposalDetailModal
            isOpen={isDetailOpen}
            onClose={() => setDetailOpen(false)}
            proposalId={selectedId}
          />
        )}
      </div>
    </CommonPage>
  );
};

export default ProposalPage; 