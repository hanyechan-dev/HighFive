import * as React from "react";
import { useParams, useNavigate } from "react-router-dom";
import ApplicationListHeader from "../components/ApplicationListHeader";
import ApplicationSummaryRow from "../components/ApplicationSummaryRow";
import type { ApplicationSummaryForCompany } from "../props/ApplicationProps";
import { ApplicationListApi } from "../apis/ApplicationApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/customHooks/usePagination";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import Button from "../../../common/components/button/Button";
import ApplicationDetailModal from "../modals/ApplicationDetailModal";

// 목데이터 - 채용공고 ID별로 다른 지원자 데이터
const getMockApplications = (jobPostingId: number): ApplicationSummaryForCompany[] => {
  const applicationsMap: Record<number, ApplicationSummaryForCompany[]> = {
    1: [ // 프론트엔드 개발자 모집
      {
        id: 1,
        jobPostingId: 1,
        jobPostingTitle: "프론트엔드 개발자 모집",
        name: "김철수",
        genderType: "남성",
        birthDate: "1990-05-15",
        hasCareer: true,
        job: "프론트엔드 개발자",
        educationLevel: "대졸",
        createdDate: "2024-01-15",
        isPassed: false
      },
      {
        id: 2,
        jobPostingId: 1,
        jobPostingTitle: "프론트엔드 개발자 모집",
        name: "이영희",
        genderType: "여성",
        birthDate: "1992-08-23",
        hasCareer: false,
        job: "프론트엔드 개발자",
        educationLevel: "대졸",
        createdDate: "2024-01-14",
        isPassed: true
      },
      {
        id: 3,
        jobPostingId: 1,
        jobPostingTitle: "프론트엔드 개발자 모집",
        name: "박지민",
        genderType: "남성",
        birthDate: "1995-03-10",
        hasCareer: true,
        job: "프론트엔드 개발자",
        educationLevel: "석사",
        createdDate: "2024-01-13",
        isPassed: false
      }
    ],
    2: [ // 백엔드 개발자 모집
      {
        id: 4,
        jobPostingId: 2,
        jobPostingTitle: "백엔드 개발자 모집",
        name: "정민수",
        genderType: "남성",
        birthDate: "1988-11-30",
        hasCareer: true,
        job: "백엔드 개발자",
        educationLevel: "대졸",
        createdDate: "2024-01-12",
        isPassed: true
      },
      {
        id: 5,
        jobPostingId: 2,
        jobPostingTitle: "백엔드 개발자 모집",
        name: "최유진",
        genderType: "여성",
        birthDate: "1993-07-18",
        hasCareer: false,
        job: "백엔드 개발자",
        educationLevel: "대졸",
        createdDate: "2024-01-11",
        isPassed: false
      },
      {
        id: 6,
        jobPostingId: 2,
        jobPostingTitle: "백엔드 개발자 모집",
        name: "강동원",
        genderType: "남성",
        birthDate: "1991-12-05",
        hasCareer: true,
        job: "백엔드 개발자",
        educationLevel: "석사",
        createdDate: "2024-01-10",
        isPassed: true
      },
      {
        id: 7,
        jobPostingId: 2,
        jobPostingTitle: "백엔드 개발자 모집",
        name: "윤서연",
        genderType: "여성",
        birthDate: "1994-04-20",
        hasCareer: false,
        job: "백엔드 개발자",
        educationLevel: "대졸",
        createdDate: "2024-01-09",
        isPassed: false
      }
    ]
  };

  return applicationsMap[jobPostingId] || [];
};

export default function ApplicationPage() {
  const { jobPostingId } = useParams<{ jobPostingId: string }>();
  const [applications, setApplications] = React.useState<ApplicationSummaryForCompany[]>([]);
  const [totalElements, setTotalElements] = React.useState(0);
  const [isLoading, setIsLoading] = React.useState(false);
  const [selectedApplicationId, setSelectedApplicationId] = React.useState<number | null>(null);
  const [isDetailModalOpen, setIsDetailModalOpen] = React.useState(false);
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

  React.useEffect(() => {
    if (!jobPostingId) return;
    
    const currentMockApplications = getMockApplications(Number(jobPostingId));
    
    const fetchApplications = async () => {
      setIsLoading(true);
      try {
        const res = await ApplicationListApi(Number(jobPostingId), clickedPage - 1, 10);
        if (res && res.content) {
          setApplications(res.content);
          setTotalElements(res.totalElements);
        } else {
          setApplications(currentMockApplications);
          setTotalElements(currentMockApplications.length);
        }
      } catch (err) {
        console.log("API 에러:", err);
        setApplications(currentMockApplications);
        setTotalElements(currentMockApplications.length);
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
} 