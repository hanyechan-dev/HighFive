import type { CareerDescriptionSummaryDto, CareerDescriptionResponseDto, CoverLetterSummaryDto, CoverLetterResponseDto } from "../../myPageForMember/props/myPageForMemberProps";
import type { JobPostingMainCardDto, JobPostingUnderCardDto, JobPostingSummaryForMemberDto, JobPostingForMemberResponseDto, Resume } from "../props/JobPostingForMemberProps";



export interface JobPostingForMemberPageState {
    jobPostingMainCardDtos: JobPostingMainCardDto[];
    jobPostingUnderCardDtos : JobPostingUnderCardDto[];
    jobPostingForMemberSummaryDtos: JobPostingSummaryForMemberDto[];
    showJobPostingFilterModal : boolean;
    clickedJobPostingId : number;
    showJobPostingForMemberDetailModal: boolean;
    jobPostingForMemberResponseDto: JobPostingForMemberResponseDto;
    showApplicationModal: boolean;
    showModalNumber: number;
    resume: Resume
    clickedCareerDescriptionId: number;
    careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[];
    careerDescriptionResponseDto: CareerDescriptionResponseDto;
    clickedCoverLetterId: number;
    coverLetterSummaryDtos: CoverLetterSummaryDto[];
    coverLetterResponseDto: CoverLetterResponseDto;
}


export type JobPostingForMemberPageAction =
    | { type: "SET_JOB_POSTING_FOR_MEMBER_MAIN_CARD_DTOS"; payload: JobPostingMainCardDto[] }
    | { type: "SET_JOB_POSTING_FOR_MEMBER_UNDER_CARD_DTOS"; payload: JobPostingUnderCardDto[] }
    | { type: "SET_JOB_POSTING_FOR_MEMBER_SUMMARY_DTOS"; payload: JobPostingSummaryForMemberDto[] }
    | { type: "SET_SHOW_JOB_POSTING_FILTER_MODAL"; payload: boolean }
    | { type: "SET_CLICKED_JOB_POSTING_ID"; payload: number }
    | { type: "SET_SHOW_JOB_POSTING_FOR_MEMBER_DETAIL_MODAL"; payload: boolean }
    | { type: "SET_JOB_POSTING_FOR_MEMBER_RESPONSE_DTO"; payload: JobPostingForMemberResponseDto }
    | { type: "SET_SHOW_APPLICATION_MODAL"; payload: boolean }
    | { type: "SET_SHOW_MODAL_NUMBER"; payload: number }
    | { type: "SET_RESUME"; payload: Resume }
    | { type: "SET_CLICKED_CAREER_DESCRIPTION_ID"; payload: number }
    | { type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS"; payload: CareerDescriptionSummaryDto[] }
    | { type: "SET_CAREER_DESCRIPTION_RESPONSE_DTO"; payload: CareerDescriptionResponseDto }
    | { type: "SET_CLICKED_COVER_LETTER_ID"; payload: number }
    | { type: "SET_COVER_LETTER_SUMMARY_DTOS"; payload: CoverLetterSummaryDto[] }
    | { type: "SET_COVER_LETTER_RESPONSE_DTO"; payload: CoverLetterResponseDto };
