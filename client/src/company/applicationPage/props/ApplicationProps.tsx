// 지원자 리스트(기업용) DTO 타입
export type ApplicationSummaryForCompany = {
  id: number;
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