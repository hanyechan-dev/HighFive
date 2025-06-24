import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { usePagination } from "../../../common/customHooks/usePagination";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { ApplicationSummaryForCompany } from "../props/ApplicationProps";
import { ApplicationListApi } from "../apis/ApplicationApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import ApplicationListHeader from "../components/ApplicationListHeader";
import ApplicationSummaryRow from "../components/ApplicationSummaryRow";
import Pagination from "../../../common/components/pagination/Pagination";
import ApplicationDetailModal from "../modals/ApplicationDetailModal";
import { getMockApplications } from "../../common/mockData/CompanyMockData";
import Button from "../../../common/components/button/Button";

const ApplicationPage = () => {
  const { jobPostingId } = useParams<{ jobPostingId: string }>();
  const [applications, setApplications] = useState<ApplicationSummaryForCompany[]>([]);
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
    const fetchApplications = async () => {
      setIsLoading(true);
      try {
        const res = await ApplicationListApi(Number(jobPostingId), clickedPage - 1, 10);
        if (res && res.data.content) {
          setApplications(res.data.content);
          setTotalElements(res.data.totalElements);
        } else {
          setApplications(getMockApplications(Number(jobPostingId)));
          setTotalElements(getMockApplications(Number(jobPostingId)).length);
        }
      } catch (err) {
        console.log("API 에러:", err);
        setApplications(getMockApplications(Number(jobPostingId)));
        setTotalElements(getMockApplications(Number(jobPostingId)).length);
      } finally {
        setIsLoading(false);
      }
    };
    fetchApplications();
  }, [jobPostingId, clickedPage]);

  const handleApplicationClick = (applicationId: number) => {
    setSelectedApplicationId(applicationId);
    setIsDetailModalOpen(true);
  };

  const handleCloseDetailModal = () => {
    setIsDetailModalOpen(false);
    setSelectedApplicationId(null);
  };

  console.log("렌더링 상태:", { isLoading, applicationsLength: applications.length });

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="지원자 리스트"
            description="해당 채용공고에 지원한 지원자 목록입니다"
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
          <div className="text-center py-12 text-gray-400">로딩 중...</div>
        ) : applications.length === 0 ? (
          <CompanyEmptyState
            title="지원자가 없습니다."
            text="아직 지원자가 없습니다."
          />
        ) : (
          <>
            <ApplicationListHeader />
            {applications.map((app) => (
              <ApplicationSummaryRow 
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
        <ApplicationDetailModal
          isOpen={isDetailModalOpen}
          onClose={handleCloseDetailModal}
          applicationId={selectedApplicationId}
        />
      )}
    </CommonPage>
  );
};

export default ApplicationPage; 