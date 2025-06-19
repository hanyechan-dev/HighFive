import type { Resume } from "../../../request/props/RequestProps";
import type { CareerDescriptionSummaryDto, CareerDescriptionResponseDto, CoverLetterSummaryDto, CoverLetterResponseDto } from "../../props/myPageForMemberProps";




export interface DocumentTabState {
    showDocumentTab: "이력서" | "경력기술서" | "자기소개서";
    resume: Resume
    clickedCareerDescriptionId: number;
    careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[];
    careerDescriptionResponseDto: CareerDescriptionResponseDto;
    clickedCoverLetterId: number;
    coverLetterSummaryDtos: CoverLetterSummaryDto[];
    coverLetterResponseDto: CoverLetterResponseDto;
    showModal: boolean;
    showModalType: "create" | "detail" | "update";

}


export type DocumentTabAction =
    | { type: "SET_SHOW_DOCUMENT_TAB"; payload: "이력서" | "경력기술서" | "자기소개서" }
    | { type: "SET_RESUME"; payload: Resume }
    | { type: "SET_CLICKED_CAREER_DESCRIPTION_ID"; payload: number }
    | { type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS"; payload: CareerDescriptionSummaryDto[] }
    | { type: "SET_CAREER_DESCRIPTION_RESPONSE_DTO"; payload: CareerDescriptionResponseDto }
    | { type: "SET_CLICKED_COVER_LETTER_ID"; payload: number }
    | { type: "SET_COVER_LETTER_SUMMARY_DTOS"; payload: CoverLetterSummaryDto[] }
    | { type: "SET_COVER_LETTER_RESPONSE_DTO"; payload: CoverLetterResponseDto }
    | { type: "SET_SHOW_MODAL"; payload: boolean }
    | { type: "SET_SHOW_MODAL_TYPE"; payload: "create" | "detail" | "update" }

