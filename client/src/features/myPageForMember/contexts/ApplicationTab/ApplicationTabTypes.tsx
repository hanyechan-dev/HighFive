import type { ApplicationSummaryForMemberDto, ApplicationResponseDto } from "../../props/myPageForMemberProps";




export interface ApplicationTabState {
    applicationSummaryForMemberDtos : ApplicationSummaryForMemberDto[];
    showModal : boolean;
    applicationResponseDto : ApplicationResponseDto;
    totalElements : number;
}

export type ApplicationTabAction =
    | { type: "SET_APPLICATION_SUMMARY_FOR_MEMBER_DTOS"; payload: ApplicationSummaryForMemberDto[] }
    | { type: "SET_SHOW_MODAL"; payload: boolean }
    | { type: "SET_APPLICATION_RESPONSE_DTO"; payload: ApplicationResponseDto }
    | { type: "SET_TOTAL_ELEMENTS"; payload: number }

