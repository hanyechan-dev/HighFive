import type { RequestSummaryDto, RequestDetailDto, CompletedRequestDetailDto, Resume, CareerDescriptionSummaryDto, CoverLetterSummaryDto } from "../props/RequestProps";

export interface RequestPageState {
    requestSummaryDtos: RequestSummaryDto[];
    showRequestModal: boolean;
    showRequestDetailModal: boolean;
    requestDetailDto: RequestDetailDto;
    completedRequestDetailDto: CompletedRequestDetailDto;
    isCompleted: boolean;
    targetJob: string;
    targetCompanyName: string;
    showModalNumber: number;
    clickedCareerDescriptionId: number;
    clickedCoverLetterId: number;
    resume: Resume
    careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[];
    coverLetterSummaryDtos: CoverLetterSummaryDto[];
}


export type RequestPageAction =
    | { type: "SET_REQUEST_SUMMARY_DTOS"; payload: RequestSummaryDto[] }
    | { type: "SET_SHOW_REQUEST_MODAL"; payload: boolean }
    | { type: "SET_SHOW_REQUEST_DETAIL_MODAL"; payload: boolean }
    | { type: "SET_REQUEST_DETAIL_DTO"; payload: RequestDetailDto }
    | { type: "SET_COMPLETED_REQUEST_DETAIL_DTO"; payload: CompletedRequestDetailDto }
    | { type: "SET_IS_COMPLETED"; payload: boolean }
    | { type: "SET_TARGET_JOB"; payload: string }
    | { type: "SET_TARGET_COMPANY_NAME"; payload: string }
    | { type: "SET_SHOW_MODAL_NUMBER"; payload: number }
    | { type: "SET_CLICKED_CAREER_DESCRIPTION_ID"; payload: number }
    | { type: "SET_CLICKED_COVER_LETTER_ID"; payload: number }
    | { type: "SET_RESUME"; payload: Resume }
    | { type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS"; payload: CareerDescriptionSummaryDto[] }
    | { type: "SET_COVER_LETTER_SUMMARY_DTOS"; payload: CoverLetterSummaryDto[] };