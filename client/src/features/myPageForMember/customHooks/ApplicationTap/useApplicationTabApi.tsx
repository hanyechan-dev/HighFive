import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import { readMyProposalApi, readMyProposalsApi } from "../../apis/MyPageForMemberApi";
import type { ApplicationResponseDto, ApplicationSummaryForMemberDto } from "../../props/myPageForMemberProps";
import { useApplicationTabController } from "./useApplicationTabController";


export const useApplicationTabApi = () => {
    const { 
        setApplicationSummaryForMemberDtos,
        setTotalElements,
        setApplicationResponseDto,
    } = useApplicationTabController();

    const readApplications = async (page: number, size: number) => {
        try {

            const response = await readMyProposalsApi(page, size);
            const applicationSummaryForMemberDtos: ApplicationSummaryForMemberDto[] = response.data.content;
            setApplicationSummaryForMemberDtos(applicationSummaryForMemberDtos);
            setTotalElements(response.data.totalElements);
        } catch (err) {
            printErrorInfo(err);
        }

    }

    const readApplication = async (id: number) => {
        try {
            const applicationResponseDto: ApplicationResponseDto = (await readMyProposalApi(id)).data;
            setApplicationResponseDto(applicationResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    }

    return {
        readApplications,
        readApplication,
    }


}