// 합격자 리스트(기업용) DTO 타입
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
  name: string;
  email: string;
  genderType: string;
  birthDate: string;
  job: string;
  phone: string;
  createdDate: string;
  isPassed: boolean;
  resumeJson: string;
  coverLetterJson: string;
  careerDescriptionJson: string;
}; 