import { Request } from "../../api/Request";
import type { ProposalSummary, ProposalDetail } from "../props/ProposalProps";

interface ProposalListResponse {
  content: ProposalSummary[];
  totalElements: number;
  totalPages: number;
}

export const ProposalListApi = (page: number, size: number = 10) => {
  return Request.post<ProposalListResponse>('/companies/proposals', {
    page,
    size
  });
};

export const ProposalDetailApi = (id: number) => {
  return Request.post<ProposalDetail>('/companies/proposals/detail', { id });
}; 