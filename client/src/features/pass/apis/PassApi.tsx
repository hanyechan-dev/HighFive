import { api } from "../../../common/Axios";
import type { ApplicationSummaryForCompany, ApplicationDetail } from "../props/PassProps";
import type { JobPostingSummary } from "../../../common/props/JobPostingTypes";

interface PassListResponse {
  content: ApplicationSummaryForCompany[];
  totalElements: number;
  totalPages: number;
}

interface JobPostingListResponse {
  content: JobPostingSummary[];
  totalElements: number;
  totalPages: number;
}

// 합격자가 있는 채용공고 목록 조회
export const PassJobPostingListApi = (page: number, size: number) => {
  return api(true).get<JobPostingListResponse>('/passes', {
    params: { page, size }
  });
};

// 합격자 목록 조회
export const PassListApi = (jobPostingId: number, page: number, size: number) => {
  return api(true).post<PassListResponse>('/passes/applications', { 
    id: jobPostingId
  }, {
    params: { page, size }
  });
};

// 합격자 상세 조회
export const PassDetailApi = (applicationId: number) => {
  return api(true).post<ApplicationDetail>('/passes/applications/detail', { id: applicationId });
}; 