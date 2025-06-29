import { useEffect, useState } from "react";
import CommonPage from "../../../common/pages/CommonPage";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { AiConsultingListApi, AiConsultingApprovalApi } from "../apis/AiConsultingApi";
import type { AiConsultingSummary } from "../props/AiConsultingProps";
import AiConsultingListHeader from "../components/AiConsultingListHeader";
import AiConsultingSummaryRow from "../components/AiConsultingSummaryRow";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import PageTitle from "../../../common/components/title/PageTitle";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/customHooks/usePagination";

const AiConsultingPage = () => {
  const [consultings, setConsultings] = useState<AiConsultingSummary[]>([]);
  const [totalElements, setTotalElements] = useState(0);
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
    const fetchConsultings = async () => {
      setIsLoading(true);
      try {
        const res = await AiConsultingListApi(clickedPage - 1, 10);
        if (res && res.data.content) {
          setConsultings(res.data.content);
          setTotalElements(res.data.totalElements);
        } else {
          setConsultings([]);
          setTotalElements(0);
        }
      } catch (err) {
        printErrorInfo(err);
        setConsultings([]);
        setTotalElements(0);
      } finally {
        setIsLoading(false);
      }
    };
    fetchConsultings();
  }, [clickedPage]);

  const handleViewDetail = (id: number) => {
    // TODO: 상세보기 모달 구현
    console.log("상세보기:", id);
  };

  const handleApprove = async (id: number) => {
    if (!window.confirm('정말 승인하시겠습니까?')) return;
    try {
      await AiConsultingApprovalApi(id);
      alert('승인되었습니다.');
      // 목록 새로고침
      const res = await AiConsultingListApi(clickedPage - 1, 10);
      if (res && res.data.content) {
        setConsultings(res.data.content);
        setTotalElements(res.data.totalElements);
      }
    } catch (err) {
      printErrorInfo(err);
      alert('승인에 실패했습니다.');
    }
  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="AI 컨설팅 관리"
            description="AI 컨설팅 요청을 관리하세요"
          />
        </div>
        
        {isLoading ? (
          <LoadingSpinner message="AI 컨설팅을 불러오는 중..." />
        ) : (
          <>
            <AiConsultingListHeader />
            {consultings.length === 0 ? (
              <EmptyState
                title="등록된 AI 컨설팅이 없습니다."
                text="현재 처리할 AI 컨설팅 요청이 없습니다."
              />
            ) : (
              consultings.map((consulting) => (
                <AiConsultingSummaryRow
                  key={consulting.aiConsultingId}
                  consulting={consulting}
                  onViewDetail={handleViewDetail}
                  onApprove={handleApprove}
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
      </div>
    </CommonPage>
  );
};

export default AiConsultingPage; 