import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import PassJobPostingListHeader from "../components/PassJobPostingListHeader";
import PassJobPostingSummaryRow from "../components/PassJobPostingSummaryRow";
import type { JobPostingSummary } from "../../common/types/JobPostingTypes";

const PassJobPostingPage = () => {
  const navigate = useNavigate();
  const [jobPostings, setJobPostings] = useState<JobPostingSummary[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // TODO: 채용공고 목록 API 호출
    const fetchJobPostings = async () => {
      try {
        // 임시 데이터
        const mockData: JobPostingSummary[] = [
          {
            id: 1,
            title: "프론트엔드 개발자",
            companyName: "테크컴퍼니",
            type: "IT",
            job: "개발",
            workLocation: "서울",
            careerType: "경력",
            educationLevel: "대졸",
            createdDate: "2024-01-15"
          },
          {
            id: 2,
            title: "백엔드 개발자",
            companyName: "스타트업",
            type: "IT",
            job: "개발",
            workLocation: "부산",
            careerType: "신입",
            educationLevel: "대졸",
            createdDate: "2024-01-10"
          }
        ];
        setJobPostings(mockData);
      } catch (error) {
        console.error("채용공고 목록 조회 실패:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchJobPostings();
  }, []);

  const handleShowPasses = (jobPostingId: number) => {
    navigate(`/job-posting/${jobPostingId}/passes`);
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="text-gray-500">로딩 중...</div>
      </div>
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
        
        <PassJobPostingListHeader />
        {jobPostings.length > 0 ? (
          jobPostings.map((jobPosting) => (
            <PassJobPostingSummaryRow
              key={jobPosting.id}
              job={jobPosting}
              onShowPasses={handleShowPasses}
            />
          ))
        ) : (
          <div className="text-center py-12 text-gray-500">
            등록된 채용공고가 없습니다.
          </div>
        )}
      </div>
    </CommonPage>
  );
};

export default PassJobPostingPage; 