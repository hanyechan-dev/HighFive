import { api } from "../../../common/Axios";
import type { ApplicationSummaryForCompany, ApplicationDetail } from "../props/ApplicationProps";

interface ApplicationListResponse {
  content: ApplicationSummaryForCompany[];
  totalElements: number;
  totalPages: number;
}

export const ApplicationListApi = (jobPostingId: number, page: number, size: number) => {
  return api(true).post<ApplicationListResponse>('/appliers/applications', { 
    id: jobPostingId,
    page,
    size,
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