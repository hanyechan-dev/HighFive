import type { DocumentTabAction, DocumentTabState } from "./DocumentTabTypes";

export const initialState: DocumentTabState = {
    showDocumentTab : "이력서",
    resume: {
        educationResponseDtos: [],
        careerResponseDtos: [],
        certificationResponseDtos: [],
        languageTestResponseDtos: []
    },
    clickedCareerDescriptionId: -1,
    careerDescriptionSummaryDtos: [],
    careerDescriptionResponseDto: {
        id: -1,
        title: "",
        contents: [],
        createdDate: ""

    },
    clickedCoverLetterId: -1,
    coverLetterSummaryDtos: [],
    coverLetterResponseDto: {
        id: -1,
        title: "",
        contents: [],
        createdDate: ""
    },
    showDetailModal: false,
    showCreateModal: false,
}


export const reducer = (state: DocumentTabState, action: DocumentTabAction): DocumentTabState => {
    switch (action.type) {
        case "SET_SHOW_DOCUMENT_TAB":
            return { ...state, showDocumentTab : action.payload};
        case "SET_RESUME":
            return { ...state, resume: action.payload };
        case "SET_CLICKED_CAREER_DESCRIPTION_ID":
            return { ...state, clickedCareerDescriptionId: action.payload };
        case "SET_CAREER_DESCRIPTION_SUMMARY_DTOS":
            return { ...state, careerDescriptionSummaryDtos: action.payload };
        case "SET_CAREER_DESCRIPTION_RESPONSE_DTO":
            return { ...state, careerDescriptionResponseDto: action.payload };
        case "SET_CLICKED_COVER_LETTER_ID":
            return { ...state, clickedCoverLetterId: action.payload };
        case "SET_COVER_LETTER_SUMMARY_DTOS":
            return { ...state, coverLetterSummaryDtos: action.payload };
        case "SET_COVER_LETTER_RESPONSE_DTO":
            return { ...state, coverLetterResponseDto: action.payload };
        case "SET_SHOW_DETAIL_MODAL":
            return { ...state, showDetailModal: action.payload };
        case "SET_SHOW_CREATE_MODAL":
            return { ...state, showCreateModal: action.payload };
        default:
            return state;
    }
};
