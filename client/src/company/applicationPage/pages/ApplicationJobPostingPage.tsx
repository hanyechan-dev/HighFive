import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/customHooks/usePagination";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { JobPostingSummary } from "../../common/types/JobPostingTypes";
import ApplicationJobPostingListHeader from "../components/ApplicationJobPostingListHeader";
import ApplicationJobPostingSummaryRow from "../components/ApplicationJobPostingSummaryRow";
import { JobPostingListApi } from "../../jobPostingPage/apis/JobPostingApi";

// 임시 mock 데이터
const mockJobPostings: JobPostingSummary[] = [
  {
    id: 1,
    title: "프론트엔드 개발자 모집",
    companyName: "테크컴퍼니",
    type: "대기업",
    job: "프론트엔드 개발자",
    workLocation: "서울특별시",
    careerType: "1~3년",
    educationLevel: "학사",
    createdDate: "2024-01-15"
  },
  {
    id: 2,
    title: "백엔드 개발자 모집",
    companyName: "스타트업",
    type: "중소기업",
    job: "백엔드 개발자",
    workLocation: "서울특별시",
    careerType: "신입",
    educationLevel: "석사",
    createdDate: "2024-01-10"
  }
];

const ApplicationJobPostingPage = () => {
  const [jobPostings, setJobPostings] = useState<JobPostingSummary[]>(mockJobPostings);
  const [totalElements, setTotalElements] = useState(mockJobPostings.length);
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
        const res = await JobPostingListApi(clickedPage - 1, 10);
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

  const handleShowApplicants = (jobPostingId: number) => {
    navigate(`/job-posting/${jobPostingId}/applicants`);
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
          <div className="text-center py-12 text-gray-400">로딩 중...</div>
        ) : (
          <>
            <ApplicationJobPostingListHeader />
            {jobPostings.length === 0 ? (
              <CompanyEmptyState
                title="등록된 채용공고가 없습니다."
                text="채용공고를 먼저 등록하세요."
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