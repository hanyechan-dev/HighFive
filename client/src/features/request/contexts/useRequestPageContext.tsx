import { createContext, useContext, useReducer, type ReactNode } from "react";
import type { Resume, CareerDescriptionSummaryDto, CoverLetterSummaryDto, CompletedRequestDetailDto, RequestDetailDto, RequestSummaryDto } from "../props/RequestProps";

interface RequestPageState {
    requestSummaryDtos: RequestSummaryDto[];
    showRequestModal: boolean;
    showRequestDetailModal: boolean;
    requestDetailDto: RequestDetailDto | null;
    completedRequestDetailDto: CompletedRequestDetailDto | null;
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


type RequestPageAction =
    | { type: "SET_REQUEST_SUMMARY_DTOS"; payload: RequestSummaryDto[] }
    | { type: "SET_SHOW_REQUEST_MODAL"; payload: boolean }
    | { type: "SET_SHOW_REQUEST_DETAIL_MODAL"; payload: boolean }
    | { type: "SET_REQUEST_DETAIL_DTO"; payload: RequestDetailDto | null }
    | { type: "SET_COMPLETED_REQUEST_DETAIL_DTO"; payload: CompletedRequestDetailDto | null }
    | { type: "SET_IS_COMPLETED"; payload: boolean }
    | { type: "SET_TARGET_JOB"; payload: string }
    | { type: "SET_TARGET_COMPANY_NAME"; payload: string }
    | { type: "SET_SHOW_MODAL_NUMBER"; payload: number }
    | { type: "SET_CLICKED_CAREER_DESCRIPTION_ID"; payload: number }
    | { type: "SET_CLICKED_COVER_LETTER_ID"; payload: number }
    | { type: "SET_RESUME"; payload: Resume }
    | { type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS"; payload: CareerDescriptionSummaryDto[] }
    | { type: "SET_COVER_LETTER_SUMMARY_DTOS"; payload: CoverLetterSummaryDto[] };


const initialState: RequestPageState = {
    requestSummaryDtos: [],
    showRequestModal: false,
    showRequestDetailModal: false,
    requestDetailDto: null,
    completedRequestDetailDto: null,
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

const reducer = (state: RequestPageState, action: RequestPageAction): RequestPageState => {
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

const RequestPageContext = createContext<{
    state: RequestPageState;
    dispatch: React.Dispatch<RequestPageAction>;
} | undefined>(undefined);

export const RequestPageProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <RequestPageContext.Provider value={{ state, dispatch }}>
            {children}
        </RequestPageContext.Provider>
    );
};

export const useRequestPageContext = () => {
    const context = useContext(RequestPageContext);
    if (!context) throw new Error("RequestPageContext는 Provider 안에서 사용해야 함");
    return context;
};
