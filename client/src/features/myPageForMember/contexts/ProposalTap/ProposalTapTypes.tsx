import type { ProposalSummaryForMemberDto, ProposalResponseDto } from "../../props/myPageForMemberProps";




export interface ProposalTapState {
    showModal : boolean;
    proposalSummaryForMemberDtos : ProposalSummaryForMemberDto[];
    clickedProposalId : number;
    proposalResponseDto : ProposalResponseDto;
    totalElements : number;
}


export type ProposalTapAction =
    | { type: "SET_SHOW_MODAL"; payload: boolean }
    | { type: "SET_PROPOSAL_SUMMARY_FOR_MEMBER_DTOS"; payload: ProposalSummaryForMemberDto[] }
    | { type: "SET_CLICKED_PROPOSAL_ID"; payload: number }
    | { type: "SET_PROPOSAL_RESPONSE_DTO"; payload: ProposalResponseDto }
    | { type: "SET_TOTAL_ELEMENTS"; payload: number }
