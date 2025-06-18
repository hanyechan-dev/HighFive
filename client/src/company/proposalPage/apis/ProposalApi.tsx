import { api } from "../../../common/Axios";

export const ProposalListApi = (page: number, size: number = 10) => {
  return api(true).get('/companies/proposals', {
    params: {
      page,
      size
    },
  });
};

export const ProposalDetailApi = (id: number) => {
  return api(true).post('/companies/proposals/detail', { id });
}; 