// 지원자 리스트(기업용) DTO 타입
export type ApplicationSummaryForCompany = {
  id: number;
  jobPostingId: number;
  jobPostingTitle: string;
  name: string;
  genderType: string;
  birthDate: string;
  hasCareer: boolean;
  job: string;
  educationLevel: string;
  createdDate: string;
  isPassed: boolean;
};


export type ApplicationDetail = {
  id: number;
  title: string;                   // 채용공고 제목
  companyName: string;             // 회사명
  job: string;                     // 직무
  createdDate: string;             // 지원일
  isPassed: boolean;               // 합격 여부
  resumeJson: string;              // 이력서 JSON
  coverLetterJson: string;         // 자기소개서 JSON
  careerDescriptionJson: string;   // 경력기술서 JSON
  // 지원자 개인 정보
  name: string;                    // 지원자 이름
  email: string;                   // 지원자 이메일
  gender: string;                  // 지원자 성별
  birthDate: string;               // 지원자 생년월일
  phone: string;                   // 지원자 연락처
}; 