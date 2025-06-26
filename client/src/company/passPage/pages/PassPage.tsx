import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import PassListHeader from "../components/PassListHeader";
import PassSummaryRow from "../components/PassSummaryRow";
import type { ApplicationSummaryForCompany } from "../props/PassProps";
import { PassListApi } from "../apis/PassApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/customHooks/usePagination";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import Button from "../../../common/components/button/Button";
import PassDetailModal from "../modals/PassDetailModal";
import { getMockPasses } from "../../common/mockData/CompanyMockData";
import LoadingSpinner from "../../common/components/LoadingSpinner";

const PassPage = () => {
  const { jobPostingId } = useParams<{ jobPostingId: string }>();
  const [passes, setPasses] = useState<ApplicationSummaryForCompany[]>([]);
  const [totalElements, setTotalElements] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [selectedApplicationId, setSelectedApplicationId] = useState<number | null>(null);
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
  const navigate = useNavigate();

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
    const fetchPasses = async () => {
      setIsLoading(true);
      try {
        const res = await PassListApi(Number(jobPostingId), clickedPage - 1, 10);
        if (res && res.data.content) {
          setPasses(res.data.content);
          setTotalElements(res.data.totalElements);
        } else {
          setPasses(getMockPasses(Number(jobPostingId)));
          setTotalElements(getMockPasses(Number(jobPostingId)).length);
        }
      } catch (err) {
        console.log("API 에러:", err);
        setPasses(getMockPasses(Number(jobPostingId)));
        setTotalElements(getMockPasses(Number(jobPostingId)).length);
      } finally {
        setIsLoading(false);
      }
    };
    fetchPasses();
  }, [jobPostingId, clickedPage]);

  const handleApplicationClick = (applicationId: number) => {
    setSelectedApplicationId(applicationId);
    setIsDetailModalOpen(true);
  };

  const handleCloseDetailModal = () => {
    setIsDetailModalOpen(false);
    setSelectedApplicationId(null);
  };

  console.log("렌더링 상태:", { isLoading, applicationsLength: passes.length });

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="합격자 리스트"
            description="해당 채용공고의 합격자 목록입니다"
          />
          <Button
            color="theme"
            size="m"
            text="← 채용공고 목록으로"
            type="button"
            onClick={() => navigate(-1)}
            disabled={false}
          />
        </div>
        {isLoading ? (
          <LoadingSpinner message="합격자 내역을 불러오는 중..." />
        ) : passes.length === 0 ? (
          <EmptyState
            title="합격자 내역이 없습니다."
            text="아직 합격한 지원자가 없습니다."
          />
        ) : (
          <>
            <PassListHeader />
            {passes.map((app) => (
              <PassSummaryRow 
                key={app.id} 
                application={app} 
                onClick={() => handleApplicationClick(app.id)}
              />
            ))}
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
      
      {selectedApplicationId && (
        <PassDetailModal
          isOpen={isDetailModalOpen}
          onClose={handleCloseDetailModal}
          applicationId={selectedApplicationId}
        />
      )}
    </CommonPage>
  );
};

export default PassPage; 