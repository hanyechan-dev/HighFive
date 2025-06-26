import { api } from "../../../common/Axios";
import type { MemberFilter } from "../slices/MemberPoolSlice";
import type { MemberPoolSummary, MemberPoolDetail, ProposalCreateDto } from "../props/MemberPoolProps";

interface MemberPoolListResponse {
  content: MemberPoolSummary[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export const MemberPoolPageApi = (
    filter: MemberFilter,
    page: number,
) => {
    return api(true).post<MemberPoolListResponse>('/companies/members', {
        hasCareer: filter.hasCareer,
        educationLevel: filter.educationLevel,
        address: filter.address,
        job: filter.job
    }, {
        params: { page, size: 10 }
    });
};

export const MemberPoolDetailApi = (id: number) => {
    return api(true).post<MemberPoolDetail>('/companies/members/detail', { id });
};

export const createProposalApi = (data: ProposalCreateDto) => {
    return api(true).post<void>('/companies/proposals', data);
};