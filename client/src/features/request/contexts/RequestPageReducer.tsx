import type { RequestPageAction, RequestPageState } from "./RequestPageTypes";

export const initialState: RequestPageState = {
    requestSummaryDtos: [],
    showRequestModal: false,
    showRequestDetailModal: false,
    requestDetailDto: {
        requestResponseDto: {
            id : 0,
            targetJob: "",
            targetCompanyName: "",
            consultingType: "",
            requestStatus: "",
            createdDate: "",
            resumeJson: "",
            coverLetterJson: "",
            careerDescriptionJson: ""
        },
        aiConsultingResponseDto : {
            consultingType: "",
            aiConsultingContentResponseDtos: []
        }
    },

    completedRequestDetailDto: {
        requestResponseDto: {
            id : 0,
            targetJob: "",
            targetCompanyName: "",
            consultingType: "",
            requestStatus: "",
            createdDate: "",
            resumeJson: "",
            coverLetterJson: "",
            careerDescriptionJson: ""
        },
        aiConsultingResponseDto : {
            consultingType: "",
            aiConsultingContentResponseDtos: []
        },
        consultantConsultingForMemberResponseDto : {    
            consultingType: "",
            createdDate: "", // 승인날짜
            completedDate: "", // 완료날짜
            name: "",
            consultantConsultingContentResponseDtos: []
        }
    },
    isCompleted: false,
    targetJob: '',
    targetCompanyName: '',
    showModalNumber: -1,
    clickedCareerDescriptionId: -1,
    clickedCoverLetterId: -1,
    resume: {
        educationResponseDtos: [],
        careerResponseDtos: [],
        certificationResponseDtos: [],
        languageTestResponseDtos: []
    },
    careerDescriptionSummaryDtos: [],
    coverLetterSummaryDtos: []
};

export const reducer = (state: RequestPageState, action: RequestPageAction): RequestPageState => {
    switch (action.type) {
        case "SET_REQUEST_SUMMARY_DTOS":
            return { ...state, requestSummaryDtos: action.payload };
        case "SET_SHOW_REQUEST_MODAL":
            return { ...state, showRequestModal: action.payload };
        case "SET_SHOW_REQUEST_DETAIL_MODAL":
            return { ...state, showRequestDetailModal: action.payload };
        case "SET_REQUEST_DETAIL_DTO":
            return { ...state, requestDetailDto: action.payload };
        case "SET_COMPLETED_REQUEST_DETAIL_DTO":
            return { ...state, completedRequestDetailDto: action.payload };
        case "SET_IS_COMPLETED":
            return { ...state, isCompleted: action.payload };
        case "SET_TARGET_JOB":
            return { ...state, targetJob: action.payload };
        case "SET_TARGET_COMPANY_NAME":
            return { ...state, targetCompanyName: action.payload };
        case "SET_SHOW_MODAL_NUMBER":
            return { ...state, showModalNumber: action.payload };
        case "SET_CLICKED_CAREER_DESCRIPTION_ID":
            return { ...state, clickedCareerDescriptionId: action.payload };
        case "SET_CLICKED_COVER_LETTER_ID":
            return { ...state, clickedCoverLetterId: action.payload };
        case "SET_RESUME":
            return { ...state, resume: action.payload };
        case "SET_CAREER_DESCRIPTION_SUMMARY_DTOS":
            return { ...state, careerDescriptionSummaryDtos: action.payload };
        case "SET_COVER_LETTER_SUMMARY_DTOS":
            return { ...state, coverLetterSummaryDtos: action.payload };
        default:
            return state;
    }
};
