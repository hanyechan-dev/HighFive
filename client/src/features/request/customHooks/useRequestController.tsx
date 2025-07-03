

import type { CareerDescriptionSummaryDto, CoverLetterSummaryDto } from "../../myPageForMember/props/myPageForMemberProps";
import type { Resume, RequestSummaryDto, RequestDetailDto, CompletedRequestDetailDto } from "../props/RequestProps";
import { useRequestPageContext } from "./useRequestPageContext";



export const useRequestController = () => {
    const { state, dispatch } = useRequestPageContext();
    const targetJob = state.targetJob;
    
    const setTargetJob = (targetJob: string) => {
        dispatch({
            type: "SET_TARGET_JOB",
            payload: targetJob
        });
    }
    const targetCompanyName = state.targetCompanyName;
    const setTargetCompanyName = (targetCompanyName: string) => {
        dispatch({
            type: "SET_TARGET_COMPANY_NAME",
            payload: targetCompanyName
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
    const isResumeLoading = state.isResumeLoading;
    const setIsResumeLoading = (isResumeLoading: boolean) => {
        dispatch({
            type: "SET_IS_RESUME_LOADING",
            payload: isResumeLoading
        });
    }
    
    const careerDescriptionSummaryDtos = state.careerDescriptionSummaryDtos;
    const setCareerDescriptionSummaryDtos = (careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[]) => {
        dispatch({
            type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS",
            payload: careerDescriptionSummaryDtos
        });
    }

    const isCareerDescriptionSummaryDtosLoading = state.isCareerDescriptionSummaryDtosLoading;
    const setIsCareerDescriptionSummaryDtosLoading = (isCareerDescriptionSummaryDtosLoading: boolean) => {
        dispatch({
            type: "SET_IS_CAREER_DESCRIPTION_SUMMARY_DTOS_LOADING",
            payload: isCareerDescriptionSummaryDtosLoading
        });
    }

    const coverLetterSummaryDtos = state.coverLetterSummaryDtos;
    const setCoverLetterSummaryDtos = (coverLetterSummaryDtos: CoverLetterSummaryDto[]) => {
        dispatch({
            type: "SET_COVER_LETTER_SUMMARY_DTOS",
            payload: coverLetterSummaryDtos
        });
    }

    const isCoverLetterSummaryDtosLoading = state.isCoverLetterSummaryDtosLoading;
    const setIsCoverLetterSummaryDtosLoading = (isCoverLetterSummaryDtosLoading: boolean) => {
        dispatch({
            type: "SET_IS_COVER_LETTER_SUMMARY_DTOS_LOADING",
            payload: isCoverLetterSummaryDtosLoading
        });
    }
    const clickedCareerDescriptionId = state.clickedCareerDescriptionId;
    const setClickedCareerDescriptionId = (clickedCareerDescriptionId: number) => {
        dispatch({
            type: "SET_CLICKED_CAREER_DESCRIPTION_ID",
            payload: clickedCareerDescriptionId
        });
    }
    const clickedCoverLetterId = state.clickedCoverLetterId;
    const setClickedCoverLetterId = (clickedCoverLetterId: number) => {
        dispatch({
            type: "SET_CLICKED_COVER_LETTER_ID",
            payload: clickedCoverLetterId
        });
    }
    const showModalNumber = state.showModalNumber;
    const setShowModalNumber = (showModalNumber: number) => {
        dispatch({
            type: "SET_SHOW_MODAL_NUMBER",
            payload: showModalNumber
        });
    }

    const requestSummaryDtos = state.requestSummaryDtos;
    const setRequestSummaryDtos = (requestSummaryDtos: RequestSummaryDto[]) => {
        dispatch({
            type: "SET_REQUEST_SUMMARY_DTOS",
            payload: requestSummaryDtos
        });
    }

    const isRequestSummaryDtosLoading = state.isRequestSummaryDtosLoading;
    const setIsRequestSummaryDtosLoading = (isRequestSummaryDtosLoading: boolean) => {
        dispatch({
            type: "SET_IS_REQUEST_SUMMARY_DTOS_LOADING",
            payload: isRequestSummaryDtosLoading
        });
    }

    const showRequestModal = state.showRequestModal;
    const setShowRequestModal = (showRequestModal: boolean) => {
        dispatch({
            type: "SET_SHOW_REQUEST_MODAL",
            payload: showRequestModal
        });
    }
    const showRequestDetailModal = state.showRequestDetailModal;
    const setShowRequestDetailModal = (showRequestDetailModal: boolean) => {
        dispatch({
            type: "SET_SHOW_REQUEST_DETAIL_MODAL",
            payload: showRequestDetailModal
        });
    }
    const requestDetailDto = state.requestDetailDto;
    const setRequestDetailDto = (requestDetailDto: RequestDetailDto) => {
        dispatch({
            type: "SET_REQUEST_DETAIL_DTO",
            payload: requestDetailDto
        });
    }

    const isRequestDetailDtoLoading = state.isRequestDetailDtoLoading;
    const setIsRequestDetailDtoLoading = (isRequestDetailDtoLoading: boolean) => {
        dispatch({
            type: "SET_IS_REQUEST_DETAIL_DTO_LOADING",
            payload: isRequestDetailDtoLoading
        });
    }
    const completedRequestDetailDto = state.completedRequestDetailDto;
    const setCompletedRequestDetailDto = (completedRequestDetailDto: CompletedRequestDetailDto) => {
        dispatch({
            type: "SET_COMPLETED_REQUEST_DETAIL_DTO",
            payload: completedRequestDetailDto
        });
    }
    const isCompleted = state.isCompleted;
    const setIsCompleted = (isCompleted: boolean) => {
        dispatch({
            type: "SET_IS_COMPLETED",
            payload: isCompleted
        });
    }

    return {
        targetJob,
        targetCompanyName,
        resume,
        isResumeLoading,
        careerDescriptionSummaryDtos,
        isCareerDescriptionSummaryDtosLoading,
        coverLetterSummaryDtos,
        isCoverLetterSummaryDtosLoading,
        clickedCareerDescriptionId,
        clickedCoverLetterId,
        showModalNumber,
        requestSummaryDtos,
        isRequestSummaryDtosLoading,
        showRequestModal,
        showRequestDetailModal,
        requestDetailDto,
        isRequestDetailDtoLoading,
        completedRequestDetailDto,
        isCompleted,
        setTargetJob,
        setTargetCompanyName,
        setClickedCareerDescriptionId,
        setClickedCoverLetterId,
        setShowModalNumber,
        setResume,
        setIsResumeLoading,
        setCareerDescriptionSummaryDtos,
        setIsCareerDescriptionSummaryDtosLoading,
        setCoverLetterSummaryDtos,
        setIsCoverLetterSummaryDtosLoading,
        setRequestSummaryDtos,
        setIsRequestSummaryDtosLoading,
        setShowRequestModal,
        setShowRequestDetailModal,
        setRequestDetailDto,
        setIsRequestDetailDtoLoading,
        setCompletedRequestDetailDto,
        setIsCompleted
    }
} 