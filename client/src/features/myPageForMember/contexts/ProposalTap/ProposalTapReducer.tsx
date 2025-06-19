import type { ProposalTapAction, ProposalTapState } from "./ProposalTapTypes";


export const initialState: ProposalTapState = {
    showModal : false,
    proposalSummaryForMemberDtos : [],
    clickedProposalId : -1,
    proposalResponseDto : {
        id: -1,
        proposalTitle: "",
        companyName: "",
        proposalContent: "",
        proposalJob: "",
        proposalSalary: 0,
        proposalDate: "",
        proposalStatus: ""
    },
    totalElements : 0,
}


export const reducer = (state: ProposalTapState, action: ProposalTapAction): ProposalTapState => {
    switch (action.type) {
        case "SET_SHOW_MODAL":
            return { ...state, showModal: action.payload };
        case "SET_PROPOSAL_SUMMARY_FOR_MEMBER_DTOS":
            return { ...state, proposalSummaryForMemberDtos: action.payload };
        case "SET_CLICKED_PROPOSAL_ID":
            return { ...state, clickedProposalId: action.payload };
        case "SET_PROPOSAL_RESPONSE_DTO":
            return { ...state, proposalResponseDto: action.payload };
        case "SET_TOTAL_ELEMENTS":
            return { ...state, totalElements: action.payload };
        default:
            return state;
    }
};
