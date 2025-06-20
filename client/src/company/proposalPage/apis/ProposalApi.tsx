import { api } from "../../../common/Axios";
import type { ProposalSummary, ProposalDetail } from "../props/ProposalProps";

interface ProposalListResponse {
  content: ProposalSummary[];
  totalElements: number;
  totalPages: number;
}

export const ProposalListApi = (page: number, size: number = 10) => {
  return api(true).post<ProposalListResponse>('/companies/proposals', {
    page,
    size
  });
};

export const ProposalDetailApi = (id: number) => {
  return api(true).post<ProposalDetail>('/companies/proposals/detail', { id });
}; 