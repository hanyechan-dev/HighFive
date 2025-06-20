import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import { readMyProposalApi, readMyProposalsApi, updateProposalApi } from "../../apis/MyPageForMemberApi";
import type { ProposalResponseDto, ProposalSummaryForMemberDto, ProposalUpdateDto } from "../../props/myPageForMemberProps";
import { useProposalTapController } from "./useProposalTapController";

export const useProposalTapApi = () => {
    const { 
        proposalSummaryForMemberDtos,
        setProposalSummaryForMemberDtos,
        totalElements,
        setTotalElements,
        setProposalResponseDto,
    } = useProposalTapController();

    const readProposals = async (page: number, size: number) => {
        try {

            const response = await readMyProposalsApi(page, size);
            const proposalSummaryForMemberDtos: ProposalSummaryForMemberDto[] = response.data.content;
            setProposalSummaryForMemberDtos(proposalSummaryForMemberDtos);
            setTotalElements(response.data.totalElements);
        } catch (err) {
            printErrorInfo(err);
        }

    }

    const readProposal = async (id: number) => {
        try {
            const proposalResponseDto: ProposalResponseDto = (await readMyProposalApi(id)).data.content;
            setProposalResponseDto(proposalResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    }

    const updateProposal = async (proposalUpdateDto: ProposalUpdateDto) => {
        try {
            const proposalResponseDto: ProposalResponseDto = (await updateProposalApi(proposalUpdateDto)).data.content;

            const updatedSummaryDto = {
                id: proposalResponseDto.id,
                proposalTitle: proposalResponseDto.proposalTitle,
                companyName: proposalResponseDto.companyName,
                proposalStatus: proposalResponseDto.proposalStatus,
                proposalDate: proposalResponseDto.proposalDate,
            };

            setProposalSummaryForMemberDtos(
                proposalSummaryForMemberDtos
                    .filter(dto => dto.id !== proposalResponseDto.id) // 기존 것 제거
                    .concat(updatedSummaryDto) // 새 DTO 추가
            );

            setProposalResponseDto(proposalResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    };

   

    return {
        readProposals,
        readProposal,
        updateProposal,
        totalElements,
    }


}