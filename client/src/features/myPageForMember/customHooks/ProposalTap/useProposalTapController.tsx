import type { ProposalSummaryForMemberDto, ProposalResponseDto } from "../../props/myPageForMemberProps";
import { useProposalTapContext } from "./useProposalTapContext";




export const useProposalTapController = () => {
    const { state, dispatch } = useProposalTapContext();

    const showModal = state.showModal;
    const setShowModal = (showModal: boolean) => {
        dispatch({ type: "SET_SHOW_MODAL", payload: showModal });
    };
    const proposalSummaryForMemberDtos = state.proposalSummaryForMemberDtos;
    const setProposalSummaryForMemberDtos = (proposalSummaryForMemberDtos: ProposalSummaryForMemberDto[]) => {
        dispatch({ type: "SET_PROPOSAL_SUMMARY_FOR_MEMBER_DTOS", payload: proposalSummaryForMemberDtos });
    };
    
    const proposalResponseDto = state.proposalResponseDto;
    const setProposalResponseDto = (proposalResponseDto: ProposalResponseDto) => {
        dispatch({ type: "SET_PROPOSAL_RESPONSE_DTO", payload: proposalResponseDto });
    };
    const totalElements = state.totalElements;
    const setTotalElements = (totalElements: number) => {
        dispatch({ type: "SET_TOTAL_ELEMENTS", payload: totalElements });
    };


    return {
        showModal,
        setShowModal,
        proposalSummaryForMemberDtos,
        setProposalSummaryForMemberDtos,
        proposalResponseDto,
        setProposalResponseDto,
        totalElements,
        setTotalElements,
    };


}