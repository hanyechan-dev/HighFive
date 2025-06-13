
import { useEffect } from "react";

import AiConsultingEditDetailModal from "./common/modals/AiConsultingEditDetailModal";
import type { aiConsultingDetailProps } from "./common/props/AiConsultingProps";
import FeedbackRequestPage from "./features/request/FeedbackRequestPage";
import JobPostingForMemberPage from "./features/jobPostingForMember/JobPostingForMemberPage";




const mockData: aiConsultingDetailProps = {
  targetCompanyName: "삼성전자",
  targetJob: "프론트엔드 개발자",
  requestedDate: "2025-06-13",
  resume: JSON.stringify({
    educations: [
      {
        id: 1,
        schoolName: "서울대학교",
        educationLevel: "학사",
        major: "컴퓨터공학과",
        gpa: 4.1,
        location: "서울",
        enterDate: "2018-03-01",
        graduateDate: "2022-02-28"
      }
    ],
    careers: [
      {
        id: 1,
        companyName: "카카오",
        job: "프론트엔드 개발자",
        department: "플랫폼팀",
        position: "사원",
        startDate: "2022-03-01",
        endDate: "2024-12-31"
      }
    ],
    certifications: [
      {
        id: 1,
        certificationName: "정보처리기사",
        issuingOrg: "한국산업인력공단",
        grade: "합격",
        score: "80",
        certificationNo: "1234567890",
        acquisitionDate: "2023-06-01"
      }
    ],
    languageTests: [
      {
        id: 1,
        languageType: "영어",
        testName: "TOEIC",
        issuingOrg: "ETS",
        grade: "Advanced",
        score: "920",
        certificationNo: "TOEIC20240601",
        acquisitionDate: "2024-06-01"
      }
    ]
  }),
  careerDescription: JSON.stringify({
    id: 1,
    title: "카카오 플랫폼팀 근무 경험",
    contents: [
      {
        id: 1,
        item: "주요 프로젝트",
        content: "React 기반 채팅 플랫폼 유지보수 및 신규 기능 개발"
      },
      {
        id: 2,
        item: "성과",
        content: "서비스 안정성 향상 및 페이지 로딩 속도 20% 개선"
      }
    ]
  }),
  coverLetter: JSON.stringify({
    id: 1,
    title: "삼성전자 지원 동기 및 포부",
    contents: [
      {
        id: 1,
        item: "지원동기",
        content: "반도체 및 전자 산업에서의 기술력과 글로벌 역량에 매료되어 지원함"
      },
      {
        id: 2,
        item: "입사 후 포부",
        content: "사용자 중심 UI/UX 설계 및 대규모 서비스 프론트엔드 개발 역량 강화"
      }
    ]
  }),
  aiContents: {
    aiContents: [
      {
        item: "자기소개서 피드백",
        content: "지원동기의 구체성이 부족합니다. 직무 관련 경험과 연결해보세요."
      },
      {
        item: "경력 기술서 피드백",
        content: "성과 수치는 명확하지만 팀 내 역할을 더 명시하면 좋습니다."
      }
    ]
  }
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
            <JobPostingForMemberPage />


        </>
    )

}

export default App
