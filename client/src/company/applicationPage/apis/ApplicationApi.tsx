import { api } from "../../../common/Axios";

export const ApplicationListApi = (jobPostingId: number, page: number, size: number) => {
  return api(true).post('/appliers/applications', { id: jobPostingId }, {
    params: { page, size },
  });
};

export const ApplicationDetailApi = (applicationId: number) => {
  return api(true).post('/appliers/applications/detail', { id: applicationId });
}; 