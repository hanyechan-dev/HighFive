
import { useEffect } from "react";
import JobPostingListTop from "./features/jobPostingForMember/components/JobPostingListTop";
import JobPostingListItem from "./features/jobPostingForMember/components/JobPostingListItem";
import type { JobPostingMainCardDto, JobPostingForMemberResponseDto, JobPostingSummaryForMemberDto } from "./features/jobPostingForMember/props/JobPostingForMemberProps";
import JobPostingMainCard from "./features/jobPostingForMember/components/JobPostingMainCard";
import JobPostingUnderCard from "./features/jobPostingForMember/components/JobPostingUnderCard";
import JobPostingDetailForMemberModal from "./features/jobPostingForMember/modals/JobPostingDetailForMemberModal";


const jobPostingSummaryForMemberDtos: JobPostingSummaryForMemberDto[] = [
  {
    id: 1,
    title: "프론트엔드 개발자",
    companyName: "카카오",
    companyType: "대기업",
    job: "웹개발",
    workLocation: "서울특별시",
    careerType: "신입",
    educationLevel: "학사 이상",
    similarityScore: 92.4,
    createdDate: "2025-06-01",
  },
  {
    id: 2,
    title: "백엔드 엔지니어",
    companyName: "토스",
    companyType: "스타트업",
    job: "서버 개발",
    workLocation: "서울특별시",
    careerType: "1~3년",
    educationLevel: "무관",
    similarityScore: 88.7,
    createdDate: "2025-06-05",
  },
  {
    id: 3,
    title: "데이터 분석가",
    companyName: "네이버",
    companyType: "대기업",
    job: "데이터 분석",
    workLocation: "경기도 성남시",
    careerType: "신입",
    educationLevel: "석사 이상",
    similarityScore: 85.1,
    createdDate: "2025-05-30",
  },
  {
    id: 4,
    title: "AI 모델 개발자",
    companyName: "업스테이지",
    companyType: "스타트업",
    job: "AI 개발",
    workLocation: "서울특별시",
    careerType: "3~5년",
    educationLevel: "박사 이상",
    similarityScore: 91.0,
    createdDate: "2025-06-10",
  },
];


const jobPostingCardDtos: JobPostingMainCardDto[] = [
  {
    id: 101,
    title: "프론트엔드 개발자",
    companyName: "카카오",
    companyType: "대기업",
    job: "웹개발",
    workLocation: "판교",
    careerType: "신입",
    educationLevel: "학사 이상",
    similarityScore: 95.2,
    createdDate: "2025-06-10",
    imageUrl: "https://designcompass.org/wp-content/uploads/2024/10/logo-kakao-1-1024x768.png",
  },
  {
    id: 102,
    title: "백엔드 개발자",
    companyName: "배달의민족",
    companyType: "중견기업",
    job: "서버 개발",
    workLocation: "서울특별시",
    careerType: "1~3년",
    educationLevel: "무관",
    similarityScore: 89.6,
    createdDate: "2025-06-12",
    imageUrl: "https://designcompass.org/wp-content/uploads/2024/04/logo-baemin-1-924x693.png",
  },
  {
    id: 103,
    title: "AI 엔지니어",
    companyName: "업스테이지",
    companyType: "스타트업",
    job: "AI 개발",
    workLocation: "서울 강남구",
    careerType: "3~5년",
    educationLevel: "석사 이상",
    similarityScore: 92.1,
    createdDate: "2025-06-08",
    imageUrl: "https://via.placeholder.com/100x100?text=Upstage",
  },
  {
    id: 104,
    title: "데이터 분석가",
    companyName: "쿠팡",
    companyType: "대기업",
    job: "데이터 분석",
    workLocation: "서울 송파구",
    careerType: "신입",
    educationLevel: "학사 이상",
    similarityScore: 87.3,
    createdDate: "2025-06-11",
    imageUrl: "https://via.placeholder.com/100x100?text=Coupang",
  },
];

export const mockJobPostingResponse: JobPostingForMemberResponseDto = {
  id: 1,
  companyName : "삼성",
  title: "프론트엔드 개발자 모집",
  companyType: "대기업",
  workingHours: "주 5일 (월~금) 09:00~18:00",
  workLocation: "서울특별시 강남구",
  job: "웹개발",
  careerType: "신입",
  educationLevel: "학사 이상",
  salary: 45000000,
  content: "혁신적인 사용자 경험을 만드는 프론트엔드 개발자를 모집합니다.",
  requirement: "React, TypeScript, HTML/CSS, Git 사용 경험",
  imageUrls: [
    "https://via.placeholder.com/600x400?text=Office",
    "https://via.placeholder.com/600x400?text=Team",
  ],
  createdDate: "2025-06-11",
  expiredDate: "2025-07-11",
};





function App() {

  useEffect(() => {
    if (!window.Kakao.isInitialized()) {
      window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
    }
  }, []);

  // 상기 유즈이펙트 수정 절대 금지



  return (
    <>
    <div className="flex">
      {jobPostingCardDtos.map(jobPostingCardDto => (
        <JobPostingMainCard jobPostingMainCardDto={jobPostingCardDto} onClick={()=>{}} />
      ))}
      </div>

      <div className="flex">
      {jobPostingSummaryForMemberDtos.map(jobPostingSummaryForMemberDto => (
        <JobPostingUnderCard jobPostingSummaryForMemberDto={jobPostingSummaryForMemberDto} onClick={()=>{}} />
      ))}
      </div>


      <JobPostingListTop />
      {jobPostingSummaryForMemberDtos.map(jobPostingSummaryForMemberDto => (
        <JobPostingListItem jobPostingSummaryForMemberDto={jobPostingSummaryForMemberDto} onClick={() => { }} key={jobPostingSummaryForMemberDto.id} />
      ))}

      <JobPostingDetailForMemberModal jobPostingForMemberResponseDto={mockJobPostingResponse} onClick={function (id: number): void {
        throw new Error("Function not implemented.");
      } } onClose={function (): void {
        throw new Error("Function not implemented.");
      } } />


    </>
  )

}

export default App
