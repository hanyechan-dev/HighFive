import { api } from "../../../common/Axios";
import type { ApplicationSummaryForCompany, ApplicationDetail } from "../props/ApplicationProps";
import type { JobPostingSummary } from "../../common/types/JobPostingTypes";

interface ApplicationListResponse {
  content: ApplicationSummaryForCompany[];
  totalElements: number;
  totalPages: number;
}

interface JobPostingListResponse {
  content: JobPostingSummary[];
  totalElements: number;
  totalPages: number;
}

// 지원자가 있는 채용공고 목록 조회
export const JobPostingWithApplicantsApi = (page: number, size: number) => {
  return api(true).get<JobPostingListResponse>('/appliers', {
    params: { page, size }
  });
};

export const ApplicationListApi = (jobPostingId: number, page: number, size: number) => {
  return api(true).post<ApplicationListResponse>('/appliers/applications', { 
    id: jobPostingId
  }, {
    params: { page, size }
  });
};

export const ApplicationDetailApi = (applicationId: number) => {
  return api(true).post<ApplicationDetail>('/appliers/applications/detail', { id: applicationId });
};

/**
 * 특정 지원서를 합격 처리하는 API
 * @param applicationId - 합격시킬 지원서 ID
 */
export const passApplicationApi = (applicationId: number) => {
  return api(true).put<void>('/appliers/applications', { id: applicationId });
}; 