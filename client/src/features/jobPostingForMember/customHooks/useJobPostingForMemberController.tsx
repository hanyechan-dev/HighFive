


import type { CareerDescriptionSummaryDto, CareerDescriptionResponseDto, CoverLetterSummaryDto, CoverLetterResponseDto } from "../../myPageForMember/props/myPageForMemberProps";
import type { JobPostingMainCardDto, JobPostingUnderCardDto, JobPostingSummaryForMemberDto, JobPostingForMemberResponseDto, Resume } from "../props/JobPostingForMemberProps";
import { useJobPostingForMemberPageContext } from "./useJobPostingForMemberPageContext";



export const useJobPostingForMemberController = () => {
    const { state, dispatch } = useJobPostingForMemberPageContext();
    
    const jobPostingMainCardDtos = state.jobPostingMainCardDtos;
    const setJobPostingMainCardDtos = (jobPostingMainCardDtos: JobPostingMainCardDto[]) => {
        dispatch({
            type: "SET_JOB_POSTING_FOR_MEMBER_MAIN_CARD_DTOS",
            payload: jobPostingMainCardDtos
        });
    }
    const jobPostingUnderCardDtos = state.jobPostingUnderCardDtos;
    const setJobPostingUnderCardDtos = (jobPostingUnderCardDtos: JobPostingUnderCardDto[]) => {
        dispatch({
            type: "SET_JOB_POSTING_FOR_MEMBER_UNDER_CARD_DTOS",
            payload: jobPostingUnderCardDtos
        });
    }
    const jobPostingForMemberSummaryDtos = state.jobPostingForMemberSummaryDtos;
    const setJobPostingForMemberSummaryDtos = (jobPostingForMemberSummaryDtos: JobPostingSummaryForMemberDto[]) => {
        dispatch({
            type: "SET_JOB_POSTING_FOR_MEMBER_SUMMARY_DTOS",
            payload: jobPostingForMemberSummaryDtos
        });
    }

    const showJobPostingFilterModal = state.showJobPostingFilterModal;
    const setShowJobPostingFilterModal = (showJobPostingFilterModal : boolean) => {
        dispatch({
            type: "SET_SHOW_JOB_POSTING_FILTER_MODAL",
            payload: showJobPostingFilterModal
        });
    }

    const clickedJobPostingId = state.clickedJobPostingId;
    const setClickedJobPostingId = (clickedJobPostingId: number) => {
        dispatch({
            type: "SET_CLICKED_JOB_POSTING_ID",
            payload: clickedJobPostingId
        });
    }


    const showJobPostingForMemberDetailModal = state.showJobPostingForMemberDetailModal;
    const setShowJobPostingForMemberDetailModal = (showJobPostingForMemberDetailModal: boolean) => {
        dispatch({
            type: "SET_SHOW_JOB_POSTING_FOR_MEMBER_DETAIL_MODAL",
            payload: showJobPostingForMemberDetailModal
        });
    }
    const jobPostingForMemberResponseDto = state.jobPostingForMemberResponseDto;
    const setJobPostingForMemberResponseDto = (jobPostingForMemberResponseDto: JobPostingForMemberResponseDto) => {
        dispatch({
            type: "SET_JOB_POSTING_FOR_MEMBER_RESPONSE_DTO",
            payload: jobPostingForMemberResponseDto
        });
    }
    const showApplicationModal = state.showApplicationModal;
    const setShowApplicationModal = (showApplicationModal: boolean) => {
        dispatch({
            type: "SET_SHOW_APPLICATION_MODAL",
            payload: showApplicationModal
        });
    }
    const showModalNumber = state.showModalNumber;
    const setShowModalNumber = (showModalNumber: number) => {
        dispatch({
            type: "SET_SHOW_MODAL_NUMBER",
            payload: showModalNumber
        });
    }
    const resume = state.resume;
    const setResume = ({
        educationResponseDtos,
        careerResponseDtos,
        certificationResponseDtos,
        languageTestResponseDtos,
    }: Resume) => dispatch({
            type: "SET_RESUME",
            payload: {
                educationResponseDtos,
                careerResponseDtos,
                certificationResponseDtos,
                languageTestResponseDtos,
            }

        });
    const clickedCareerDescriptionId = state.clickedCareerDescriptionId;
    const setClickedCareerDescriptionId = (clickedCareerDescriptionId: number) => {
        dispatch({
            type: "SET_CLICKED_CAREER_DESCRIPTION_ID",
            payload: clickedCareerDescriptionId
        });
    }
    const careerDescriptionSummaryDtos = state.careerDescriptionSummaryDtos;
    const setCareerDescriptionSummaryDtos = (careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[]) => {
        dispatch({
            type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS",
            payload: careerDescriptionSummaryDtos
        });
    }
    const careerDescriptionResponseDto = state.careerDescriptionResponseDto;
    const setCareerDescriptionResponseDto = (careerDescriptionResponseDto: CareerDescriptionResponseDto) => {
        dispatch({
            type: "SET_CAREER_DESCRIPTION_RESPONSE_DTO",
            payload: careerDescriptionResponseDto
        });
    }
    const clickedCoverLetterId = state.clickedCoverLetterId;
    const setClickedCoverLetterId = (clickedCoverLetterId: number) => {
        dispatch({
            type: "SET_CLICKED_COVER_LETTER_ID",
            payload: clickedCoverLetterId
        });
    }
    const coverLetterSummaryDtos = state.coverLetterSummaryDtos;
    const setCoverLetterSummaryDtos = (coverLetterSummaryDtos: CoverLetterSummaryDto[]) => {
        dispatch({
            type: "SET_COVER_LETTER_SUMMARY_DTOS",
            payload: coverLetterSummaryDtos
        });
    }
    const coverLetterResponseDto = state.coverLetterResponseDto;
    const setCoverLetterResponseDto = (coverLetterResponseDto: CoverLetterResponseDto) => {
        dispatch({
            type: "SET_COVER_LETTER_RESPONSE_DTO",
            payload: coverLetterResponseDto
        });
    }
    
    return {
        jobPostingMainCardDtos,
        setJobPostingMainCardDtos,
        jobPostingUnderCardDtos,
        setJobPostingUnderCardDtos,
        clickedJobPostingId,
        setClickedJobPostingId,
        jobPostingForMemberSummaryDtos,
        setJobPostingForMemberSummaryDtos,
        showJobPostingFilterModal,
        setShowJobPostingFilterModal,
        showJobPostingForMemberDetailModal,
        setShowJobPostingForMemberDetailModal,
        jobPostingForMemberResponseDto,
        setJobPostingForMemberResponseDto,
        showApplicationModal,
        setShowApplicationModal,
        showModalNumber,
        setShowModalNumber,
        resume,
        setResume,
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId,
        careerDescriptionSummaryDtos,
        setCareerDescriptionSummaryDtos,
        careerDescriptionResponseDto,
        setCareerDescriptionResponseDto,
        clickedCoverLetterId,
        setClickedCoverLetterId,
        coverLetterSummaryDtos,
        setCoverLetterSummaryDtos,
        coverLetterResponseDto,
        setCoverLetterResponseDto,
    };
    
}