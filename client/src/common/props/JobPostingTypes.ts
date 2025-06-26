// 공통으로 사용되는 채용공고 관련 타입들
export type JobPostingSummary = {
  id: number;
  title: string;
  companyName: string;
  companyType: string;
  job: string;
  workLocation: string;
  careerType: string;
  educationLevel: string;
  createdDate: string;
}; 