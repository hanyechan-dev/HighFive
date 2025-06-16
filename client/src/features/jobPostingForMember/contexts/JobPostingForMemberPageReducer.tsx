import type { JobPostingForMemberPageAction, JobPostingForMemberPageState } from "./JobPostingForMemberPageTypes";

export const initialState: JobPostingForMemberPageState = {
    jobPostingMainCardDtos: [],
    jobPostingUnderCardDtos: [],
    jobPostingForMemberSummaryDtos: [],
    showJobPostingFilterModal: false,
    clickedJobPostingId: -1,
    showJobPostingForMemberDetailModal: false,
    jobPostingForMemberResponseDto: {
        id: 0,
        title: "",
        companyName: "",
        companyType: "",
        workingHours: "",
        job: "",
        workLocation: "",
        careerType: "",
        educationLevel: "",
        salary: 0,
        content: "",
        requirement: "",
        imageUrls: [],
        createdDate: "",
        expiredDate: "",
    },
    showApplicationModal: false,
    showModalNumber: -1,
    resume: {
        educationResponseDtos: [],
        careerResponseDtos: [],
        certificationResponseDtos: [],
        languageTestResponseDtos: []
    },
    clickedCareerDescriptionId: -1,
    careerDescriptionSummaryDtos: [],
    careerDescriptionResponseDtos: [],
    clickedCoverLetterId: -1,
    coverLetterSummaryDtos: [],
    coverLetterResponseDtos: []
}


export const reducer = (state: JobPostingForMemberPageState, action: JobPostingForMemberPageAction): JobPostingForMemberPageState => {
    switch (action.type) {
        case "SET_JOB_POSTING_FOR_MEMBER_MAIN_CARD_DTOS":
            return { ...state, jobPostingMainCardDtos: action.payload };
        case "SET_JOB_POSTING_FOR_MEMBER_UNDER_CARD_DTOS":
            return { ...state, jobPostingUnderCardDtos: action.payload };
        case "SET_JOB_POSTING_FOR_MEMBER_SUMMARY_DTOS":
            return { ...state, jobPostingForMemberSummaryDtos: action.payload };
        case "SET_SHOW_JOB_POSTING_FILTER_MODAL":
            return { ...state, showJobPostingFilterModal: action.payload };
        case "SET_CLICKED_JOB_POSTING_ID":
            return { ...state, clickedJobPostingId: action.payload };
        case "SET_SHOW_JOB_POSTING_FOR_MEMBER_DETAIL_MODAL":
            return { ...state, showJobPostingForMemberDetailModal: action.payload };
        case "SET_JOB_POSTING_FOR_MEMBER_RESPONSE_DTO":
            return { ...state, jobPostingForMemberResponseDto: action.payload };
        case "SET_SHOW_APPLICATION_MODAL":
            return { ...state, showApplicationModal: action.payload };
        case "SET_SHOW_MODAL_NUMBER":
            return { ...state, showModalNumber: action.payload };
        case "SET_RESUME":
            return { ...state, resume: action.payload };
        case "SET_CLICKED_CAREER_DESCRIPTION_ID":
            return { ...state, clickedCareerDescriptionId: action.payload };
        case "SET_CAREER_DESCRIPTION_SUMMARY_DTOS":
            return { ...state, careerDescriptionSummaryDtos: action.payload };
        case "SET_CAREER_DESCRIPTION_RESPONSE_DTOS":
            return { ...state, careerDescriptionResponseDtos: action.payload };
        case "SET_CLICKED_COVER_LETTER_ID":
            return { ...state, clickedCoverLetterId: action.payload };
        case "SET_COVER_LETTER_SUMMARY_DTOS":
            return { ...state, coverLetterSummaryDtos: action.payload };
        case "SET_COVER_LETTER_RESPONSE_DTOS":
            return { ...state, coverLetterResponseDtos: action.payload };
        default:
            return state;
    }
};
