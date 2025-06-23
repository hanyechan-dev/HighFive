import { api } from "../../../common/Axios";
import type { ApplicationSummaryForCompany, ApplicationDetail } from "../props/PassProps";

interface PassListResponse {
  content: ApplicationSummaryForCompany[];
  totalElements: number;
  totalPages: number;
}

// 합격자 목록 조회
export const PassListApi = (jobPostingId: number, page: number, size: number) => {
  return api(true).post<PassListResponse>('/appliers/applications', { 
    id: jobPostingId,
    page,
    size,
  });
};

// 합격자 상세 조회
export const PassDetailApi = (applicationId: number) => {
  return api(true).post<ApplicationDetail>('/appliers/applications/detail', { id: applicationId });
}; 