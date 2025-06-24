import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import PassJobPostingListHeader from "../components/PassJobPostingListHeader";
import PassJobPostingSummaryRow from "../components/PassJobPostingSummaryRow";
import type { JobPostingSummary } from "../../common/types/JobPostingTypes";
import { PassJobPostingListApi } from "../apis/PassApi";
import { usePagination } from "../../../common/customHooks/usePagination";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import Pagination from "../../../common/components/pagination/Pagination";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import { mockJobPostings } from "../../common/mockData/CompanyMockData";

const PassJobPostingPage = () => {
  const navigate = useNavigate();
  const [jobPostings, setJobPostings] = useState<JobPostingSummary[]>([]);
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
    const fetchJobPostings = async () => {
      setIsLoading(true);
      try {
        const res = await PassJobPostingListApi(clickedPage - 1, 10);
        if (res && res.data.content) {
          setJobPostings(res.data.content);
          setTotalElements(res.data.totalElements);
        } else {
          setJobPostings(mockJobPostings);
          setTotalElements(mockJobPostings.length);
        }
      } catch (err) {
        printErrorInfo(err);
        setJobPostings(mockJobPostings);
        setTotalElements(mockJobPostings.length);
      } finally {
        setIsLoading(false);
      }
    };

    fetchJobPostings();
  }, [clickedPage]);

  const handleShowPasses = (jobPostingId: number) => {
    navigate(`/job-posting/${jobPostingId}/passes`);
  };

  if (isLoading) {
    return (
      <CommonPage>
        <div className="w-[1452px] mx-auto font-roboto">
          <div className="text-center py-12 text-gray-400">로딩 중...</div>
        </div>
      </CommonPage>
    );
  }

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="합격자 관리"
            description="채용공고별 합격자를 확인하세요"
          />
        </div>
        
        {jobPostings.length === 0 ? (
          <CompanyEmptyState
            title="합격자가 있는 채용공고가 없습니다."
            text="아직 합격자가 있는 채용공고가 없습니다."
          />
        ) : (
          <>
            <PassJobPostingListHeader />
            {jobPostings.map((jobPosting) => (
              <PassJobPostingSummaryRow
                key={jobPosting.id}
                job={jobPosting}
                onShowPasses={handleShowPasses}
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
    </CommonPage>
  );
};

export default PassJobPostingPage; 