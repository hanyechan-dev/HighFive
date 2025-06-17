import type { MemberFilter } from "../slices/MemberPoolSlice";
import {Request} from "../../api/Request"
import type { MemberPoolSummary, MemberPoolDetail, ProposalCreateDto } from "../props/MemberPoolProps";

export const MemberPoolPageApi = (
    filter: MemberFilter,
    page: number,
) => {
    return Request.post<MemberPoolSummary[]>('/companies/members', {
        hasCareer: filter.hasCareer,
        educationLevel: filter.educationLevel,
        address: filter.address,
        job: filter.job,
        page,
        size: 10
    });
};

export const MemberPoolDetailApi = (id: number) => {
    return Request.post<MemberPoolDetail>('/companies/members/detail', { id });
};

export const createProposalApi = (data: ProposalCreateDto) => {
    return Request.post<void>('/companies/proposals', data);
};