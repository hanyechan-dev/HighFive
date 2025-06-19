import type { Resume } from "../../../request/props/RequestProps";
import type { CareerDescriptionResponseDto, CareerDescriptionSummaryDto, CoverLetterResponseDto, CoverLetterSummaryDto } from "../../props/myPageForMemberProps";
import { useDocumentTabContext } from "./useDocumentTabContext";



export const useDocumentTabController = () => {
    const { state, dispatch } = useDocumentTabContext();

    const showDocumentTab = state.showDocumentTab;
    const setShowDocumentTab = (tab: string) => {
        if (
            tab === "이력서" ||
            tab === "경력기술서" ||
            tab === "자기소개서"
        ) {
            dispatch({ type: "SET_SHOW_DOCUMENT_TAB", payload: tab });
        }
    };
    const resume = state.resume;
    const setResume = (resume: Resume) => {
        dispatch({ type: "SET_RESUME", payload: resume });
    };
    const clickedCareerDescriptionId = state.clickedCareerDescriptionId;
    const setClickedCareerDescriptionId = (clickedCareerDescriptionId: number) => {
        dispatch({ type: "SET_CLICKED_CAREER_DESCRIPTION_ID", payload: clickedCareerDescriptionId });
    };
    const careerDescriptionSummaryDtos = state.careerDescriptionSummaryDtos;
    const setCareerDescriptionSummaryDtos = (careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[]) => {
        dispatch({ type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS", payload: careerDescriptionSummaryDtos });
    };
    const careerDescriptionResponseDto = state.careerDescriptionResponseDto;
    const setCareerDescriptionResponseDto = (careerDescriptionResponseDto: CareerDescriptionResponseDto) => {
        dispatch({ type: "SET_CAREER_DESCRIPTION_RESPONSE_DTO", payload: careerDescriptionResponseDto });
    };
    const clickedCoverLetterId = state.clickedCoverLetterId;
    const setClickedCoverLetterId = (clickedCoverLetterId: number) => {
        dispatch({ type: "SET_CLICKED_COVER_LETTER_ID", payload: clickedCoverLetterId });
    };
    const coverLetterSummaryDtos = state.coverLetterSummaryDtos;
    const setCoverLetterSummaryDtos = (coverLetterSummaryDtos: CoverLetterSummaryDto[]) => {
        dispatch({ type: "SET_COVER_LETTER_SUMMARY_DTOS", payload: coverLetterSummaryDtos });
    };
    const coverLetterResponseDto = state.coverLetterResponseDto;
    const setCoverLetterResponseDto = (coverLetterResponseDto: CoverLetterResponseDto) => {
        dispatch({ type: "SET_COVER_LETTER_RESPONSE_DTO", payload: coverLetterResponseDto });
    };

    const showModal = state.showModal;
    const setShowModal = (showModal: boolean) => {
        dispatch({ type: "SET_SHOW_MODAL", payload: showModal });
    };

    const showModalType = state.showModalType;
    const setShowModalType = (modalType: string) => {
        if (
            modalType === "create" ||
            modalType === "detail" ||
            modalType === "update"
        ) {
            dispatch({ type: "SET_SHOW_MODAL_TYPE", payload: modalType });
        }

    };



    return {
        showDocumentTab,
        setShowDocumentTab,
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
        showModal,
        setShowModal,
        showModalType,
        setShowModalType,
    };


};


