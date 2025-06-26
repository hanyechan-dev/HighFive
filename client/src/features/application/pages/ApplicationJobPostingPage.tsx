import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CommonPage from "../../../common/pages/CommonPage";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/customHooks/usePagination";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { JobPostingSummary } from "../../../common/props/JobPostingTypes";
import ApplicationJobPostingListHeader from "../components/ApplicationJobPostingListHeader";
import ApplicationJobPostingSummaryRow from "../components/ApplicationJobPostingSummaryRow";
import { JobPostingWithApplicantsApi } from "../apis/ApplicationApi";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import PageTitle from "../../../common/components/title/PageTitle";

const ApplicationJobPostingPage = () => {
  const [jobPostings, setJobPostings] = useState<JobPostingSummary[]>([]);
  const [totalElements, setTotalElements] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
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
    const fetchJobPostings = async () => {
      setIsLoading(true);
      try {
        const res = await JobPostingWithApplicantsApi(clickedPage - 1, 10);
        if (res && res.data.content) {
          setJobPostings(res.data.content); 
          setTotalElements(res.data.totalElements);
        } else {
          setJobPostings([]);
          setTotalElements(0);
        }
      } catch (err) {
        printErrorInfo(err);
        setJobPostings([]);
        setTotalElements(0);
      } finally {
        setIsLoading(false);
      }
    };
    fetchJobPostings();
  }, [clickedPage]);

  const handleShowApplicants = (jobPostingId: number) => {
    navigate(`/job-posting/${jobPostingId}/applications`);
  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="지원자 관리"
            description="채용공고별 지원자를 확인하세요"
          />
        </div>
        
        {isLoading ? (
          <LoadingSpinner message="지원자 관리 채용공고를 불러오는 중..." />
        ) : (
          <>
            <ApplicationJobPostingListHeader />
            {jobPostings.length === 0 ? (
              <EmptyState
                title="지원자가 있는 채용공고가 없습니다."
                text="아직 지원자가 있는 채용공고가 없습니다."
              />
            ) : (
              jobPostings.map((job) => (
                <ApplicationJobPostingSummaryRow
                  key={job.id}
                  job={job}
                  onShowApplicants={handleShowApplicants}
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

export default ApplicationJobPostingPage; 