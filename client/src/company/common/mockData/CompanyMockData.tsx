// 회사 관련 목데이터 중앙 관리 파일

// ===== 채용공고 목데이터 =====
export const mockJobPostings = [
  {
    id: 1,
    title: "프론트엔드 개발자 모집",
    companyName: "테크컴퍼니",
    companyType: "대기업",
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
    companyType: "중소기업",
    job: "백엔드 개발자",
    workLocation: "서울특별시",
    careerType: "신입",
    educationLevel: "석사",
    createdDate: "2024-01-10"
  }
];

// ===== 지원자 목데이터 =====
export const getMockApplications = (jobPostingId: number) => [
  {
    id: 1,
    jobPostingTitle: "프론트엔드 개발자 모집",
    name: "김철수",
    genderType: "남자",
    birthDate: "1990-05-15",
    hasCareer: true,
    job: "프론트엔드 개발자",
    educationLevel: "학사",
    createdDate: "2024-01-15",
    isPassed: false
  },
  {
    id: 2,
    jobPostingTitle: "백엔드 개발자 모집",
    name: "이영희",
    genderType: "여자",
    birthDate: "1992-08-23",
    hasCareer: false,
    job: "백엔드 개발자",
    educationLevel: "석사",
    createdDate: "2024-01-14",
    isPassed: true
  }
];

// ===== 합격자 목데이터 =====
export const getMockPasses = (jobPostingId: number) => [
  {
    id: 1,
    jobPostingTitle: "프론트엔드 개발자 모집",
    name: "이영희",
    genderType: "여자",
    birthDate: "1992-08-23",
    hasCareer: false,
    job: "프론트엔드 개발자",
    educationLevel: "대졸",
    createdDate: "2024-01-14",
    isPassed: true
  },
  {
    id: 2,
    jobPostingTitle: "백엔드 개발자 모집",
    name: "정민수",
    genderType: "남자",
    birthDate: "1988-11-30",
    hasCareer: true,
    job: "백엔드 개발자",
    educationLevel: "대졸",
    createdDate: "2024-01-12",
    isPassed: true
  }
];

// ===== 제안서 목데이터 =====
export const mockProposals = [
  {
    id: 1,
    name: "김개발",
    genderType: "남자",
    birthDate: "1990-05-15",
    hasCareer: true,
    job: "프론트엔드 개발자",
    educationLevel: "학사",
    proposalDate: "2024-01-15",
    proposalStatus: "WAITING"
  },
  {
    id: 2,
    name: "이서버",
    genderType: "여자",
    birthDate: "1992-08-23",
    hasCareer: false,
    job: "백엔드 개발자",
    educationLevel: "석사",
    proposalDate: "2024-01-14",
    proposalStatus: "APPROVED"
  }
];

// ===== 멤버풀 목데이터 =====
export const mockMembers = [
  {
    id: 1,
    name: "김개발",
    genderType: "남자",
    birthDate: "1990-05-15",
    job: "프론트엔드 개발자",
    hasCareer: true,
    similarityScore: 85,
    educationLevel: "학사"
  },
  {
    id: 2,
    name: "이서버",
    genderType: "여자",
    birthDate: "1992-08-23",
    job: "백엔드 개발자",
    hasCareer: false,
    similarityScore: 92,
    educationLevel: "석사"
  }
];

// ===== 스케줄 목데이터 =====
const getCurrentDate = () => {
  const now = new Date();
  const year = now.getFullYear();
  const month = now.getMonth() + 1;
  return { year, month };
};

const { year, month } = getCurrentDate();

export const mockSchedules = [
  {
    id: 1,
    title: "신입 개발자 면접",
    content: "프론트엔드 개발자 신입 면접을 진행합니다. 기술 면접 및 인성 면접을 포함합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-01`
  },
  {
    id: 2,
    title: "팀 회의",
    content: "주간 팀 회의를 진행합니다. 진행 상황 공유 및 다음 주 계획 수립을 합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-02`
  },
  {
    id: 3,
    title: "프로젝트 발표",
    content: "신규 프로젝트 발표 및 팀 구성 회의입니다. 프로젝트 개요 및 일정을 공유합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-03`
  },
  {
    id: 4,
    title: "기술 세미나",
    content: "최신 기술 트렌드 세미나를 진행합니다. React 18 및 Next.js 13에 대해 학습합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-04`
  },
  {
    id: 5,
    title: "코드 리뷰",
    content: "주간 코드 리뷰를 진행합니다. 각 팀원의 코드를 검토하고 개선점을 논의합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-05`
  },
  {
    id: 6,
    title: "고객 미팅",
    content: "주요 고객사와의 미팅을 진행합니다. 프로젝트 진행 상황 및 요구사항 변경사항을 논의합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-06`
  },
  {
    id: 7,
    title: "성과 평가",
    content: "분기별 성과 평가를 진행합니다. 각 직원별 성과 및 개선점을 논의합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-07`
  },
  {
    id: 8,
    title: "워크샵",
    content: "팀워크 향상을 위한 워크샵을 진행합니다. 팀 빌딩 활동 및 아이스브레이킹을 포함합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-08`
  },
  {
    id: 9,
    title: "연말 정산",
    content: "연말 정산 관련 서류 제출 마감일입니다. 모든 직원 필수 제출입니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-09`
  },
  {
    id: 10,
    title: "새해 계획 수립",
    content: "새해 사업 계획 수립 회의입니다. 각 부서별 목표 및 전략을 논의합니다.",
    date: `${year}-${month.toString().padStart(2, '0')}-10`
  }
];

// ===== 구독 관련 목데이터 =====
export const mockSubscriptionPlans = [
  {
    id: 1,
    name: "Basic",
    price: 50000,
    features: ["기본 채용공고 등록", "지원자 관리", "기본 분석"],
    isPopular: false
  },
  {
    id: 2,
    name: "Professional",
    price: 100000,
    features: ["무제한 채용공고", "고급 분석", "AI 매칭", "우선 지원"],
    isPopular: true
  },
  {
    id: 3,
    name: "Enterprise",
    price: 200000,
    features: ["모든 기능", "전담 매니저", "커스텀 솔루션", "24/7 지원"],
    isPopular: false
  }
];

export const mockCurrentSubscription = {
  id: 1,
  name: "Professional",
  userType: "company",
  startDate: "2024-01-01",
  endDate: "2024-12-31"
}; 