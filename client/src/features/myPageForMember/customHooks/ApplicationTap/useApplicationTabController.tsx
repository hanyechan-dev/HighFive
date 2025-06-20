import type { ApplicationResponseDto, ApplicationSummaryForMemberDto } from "../../props/myPageForMemberProps";
import { useApplicationTabContext } from "./useApplicationTabContext";




export const useApplicationTabController = () => {
    const { state, dispatch } = useApplicationTabContext();

    const showModal = state.showModal;
    const setShowModal = (showModal: boolean) => {
        dispatch({ type: "SET_SHOW_MODAL", payload: showModal });
    };
    const applicationSummaryForMemberDtos = state.applicationSummaryForMemberDtos;
    const setApplicationSummaryForMemberDtos = (applicationSummaryForMemberDtos: ApplicationSummaryForMemberDto[]) => {
        dispatch({ type: "SET_APPLICATION_SUMMARY_FOR_MEMBER_DTOS", payload: applicationSummaryForMemberDtos });
    };
    
    const applicationResponseDto = state.applicationResponseDto;
    const setApplicationResponseDto = (applicationResponseDto: ApplicationResponseDto) => {
        dispatch({ type: "SET_APPLICATION_RESPONSE_DTO", payload: applicationResponseDto });
    };
    const totalElements = state.totalElements;
    const setTotalElements = (totalElements: number) => {
        dispatch({ type: "SET_TOTAL_ELEMENTS", payload: totalElements });
    };


    return {
        showModal,
        setShowModal,
        applicationSummaryForMemberDtos,
        setApplicationSummaryForMemberDtos,
        applicationResponseDto,
        setApplicationResponseDto,
        totalElements,
        setTotalElements,
    };


}