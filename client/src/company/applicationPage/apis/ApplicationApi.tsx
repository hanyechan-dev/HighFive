import { Request } from "../../api/Request";
import { api } from "../../../common/Axios";
import type { ApplicationSummaryForCompany } from "../props/ApplicationProps";

interface ApplicationListResponse {
  content: ApplicationSummaryForCompany[];
  totalElements: number;
  totalPages: number;
}

interface ApplicationDetailResponse {
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
}

export const ApplicationListApi = (jobPostingId: number, page: number, size: number) => {
  return Request.post<ApplicationListResponse>('/appliers/applications', { 
    id: jobPostingId,
    page,
    size,
  });
};

export const ApplicationDetailApi = (applicationId: number) => {
  return Request.post<ApplicationDetailResponse>('/appliers/applications/detail', { id: applicationId });
};

/**
 * 특정 지원서를 합격 처리하는 API
 * @param applicationId - 합격시킬 지원서 ID
 */
export const passApplicationApi = (applicationId: number) => {
  return api(true).put('/appliers/applications', { id: applicationId });
}; 