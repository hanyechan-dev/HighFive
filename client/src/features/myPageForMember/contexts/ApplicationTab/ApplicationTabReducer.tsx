import type { ApplicationTabAction, ApplicationTabState } from "./ApplicationTabTypes";

export const initialState: ApplicationTabState = {
    applicationSummaryForMemberDtos : [],
    showModal : false,
    applicationResponseDto : {
        id: -1,
        title: "",
        companyName: "",
        job: "",
        createdDate: "",
        isPassed: false,
        resumeJson: "",
        coverLetterJson: "",
        careerDescriptionJson: "",
    },
    totalElements : 0
}


export const reducer = (state: ApplicationTabState, action: ApplicationTabAction): ApplicationTabState => {
    switch (action.type) {
        case "SET_APPLICATION_SUMMARY_FOR_MEMBER_DTOS":
            return { ...state, applicationSummaryForMemberDtos: action.payload };
        case "SET_SHOW_MODAL":
            return { ...state, showModal: action.payload };
        case "SET_APPLICATION_RESPONSE_DTO":
            return { ...state, applicationResponseDto: action.payload };
        case "SET_TOTAL_ELEMENTS":
            return { ...state, totalElements: action.payload };
        default:
            return state;
    }
};
