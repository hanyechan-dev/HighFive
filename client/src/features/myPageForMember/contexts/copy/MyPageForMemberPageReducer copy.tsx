import type { MyPageForMemberPageAction, MyPageForMemberPageState } from "./MyPageForMemberPageTypes";

export const initialState: MyPageForMemberPageState = {
    showMyPageTab: "회원정보",
    memberMyPageResponseDto: {
        memberResponseDto: {
            nickname: ""
        },
        myPageResponseDto: {
            email: "",
            name: "",
            birthDate: "",
            genderType: "",
            phone: "",
            address: "",
            type: ""
        }
    },
    showMemberInfoUpdateModal : false,
    showMemberInfoUpdateModalTab : "기본정보",
    myPageUpdateDto : {
        phone: "",
        address: ""
    },
    memberUpdateDto : {
        nickname: ""
    },
    passwordUpdateDto : {
        password: "",
        newPassword: "",
        newPasswordCheck: ""
    },
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
        contents: []
    },
    clickedCoverLetterId: -1,
    coverLetterSummaryDtos: [],
    coverLetterResponseDto: {
        id: -1,
        title: "",
        contents: []
    },
    educationCreateDto : {
        schoolName: "",
        educationLevel: "",
        major: "",
        gpa: "",
        location: "",
        enterDate: "",
        graduateDate: ""
    },
    educationUpdateDto : {
        id: -1,
        schoolName: "",
        educationLevel: "",
        major: "",
        gpa: "",
        location: "",
        enterDate: "",
        graduateDate: ""
    },
    careerCreateDto : {
        companyName: "",
        job: "",
        department: "",
        position: "",
        startDate: "",
        endDate: ""
    },
    careerUpdateDto : {
        id:-1,
        companyName: "",
        job: "",
        department: "",
        position: "",
        startDate: "",
        endDate: ""
    },
    certificationCreateDto : {
        certificationName: "",
        issuingOrg: "",
        grade: "",
        score: "",
        certificationNo: "",
        acquisitionDate: ""
    },
    certificationUpdateDto : {
        id: -1,
        certificationName: "",
        issuingOrg: "",
        grade: "",
        score: "",
        certificationNo: "",
        acquisitionDate: ""
    },
    languageTestCreateDto : {
        languageType: "",
        testName: "",
        issuingOrg: "",
        grade: "",
        score: "",
        certificationNo: "",
        acquisitionDate: ""
    },
    languageTestUpdateDto : {
        id: -1,
        languageType: "",
        testName: "",
        issuingOrg: "",
        grade: "",
        score: "",
        certificationNo: "",
        acquisitionDate: ""
    },
    careerDescriptionCreateDto : {
        title: "",
        contents: []
    },
    careerDescriptionUpdateDto : {
        id: -1,
        title: "",
        contents: []
    },
    coverLetterCreateDto : {
        title: "",
        contents: []
    },
    coverLetterUpdateDto : {
        id: -1,
        title: "",
        contents: []
    },
    proposalSummaryForMemberDtos : [],
    proposalResponseDto : {
        id: -1,
        proposalTitle: "",
        companyName: "",
        proposalContent: "",
        proposalJob: "",
        proposalSalary: 0,
        proposalDate: "",
        proposalStatus: ""
    },
    proposalUpdateDto : {
        id: -1,
        proposalStatus: ""
    },
    applicationSummaryForMemberDtos : [],
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
    paymentResponseDtos : []
}


export const reducer = (state: MyPageForMemberPageState, action: MyPageForMemberPageAction): MyPageForMemberPageState => {
    switch (action.type) {
        case "SET_SHOW_MY_PAGE_TAB":
            return { ...state, showMyPageTab: action.payload };
        case "SET_MEMBER_MY_PAGE_RESPONSE_DTO":
            return { ...state, memberMyPageResponseDto: action.payload };
        case "SET_SHOW_MEMBER_INFO_UPDATE_MODAL":
            return { ...state, showMemberInfoUpdateModal: action.payload };
        case "SET_SHOW_MEMBER_INFO_UPDATE_MODAL_TAB":
            return { ...state, showMemberInfoUpdateModalTab: action.payload };
        case "SET_MY_PAGE_UPDATE_DTO":
            return { ...state, myPageUpdateDto: action.payload };
        case "SET_MEMBER_UPDATE_DTO":
            return { ...state, memberUpdateDto: action.payload };
        case "SET_PASSWORD_UPDATE_DTO":
            return { ...state, passwordUpdateDto: action.payload };
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
        case "SET_EDUCATION_CREATE_DTO":
            return { ...state, educationCreateDto: action.payload };
        case "SET_EDUCATION_UPDATE_DTO":
            return { ...state, educationUpdateDto: action.payload };
        case "SET_CAREER_CREATE_DTO":
            return { ...state, careerCreateDto: action.payload };
        case "SET_CAREER_UPDATE_DTO":
            return { ...state, careerUpdateDto: action.payload };
        case "SET_CERTIFICATION_CREATE_DTO":
            return { ...state, certificationCreateDto: action.payload };
        case "SET_CERTIFICATION_UPDATE_DTO":
            return { ...state, certificationUpdateDto: action.payload };
        case "SET_LANGUAGE_TEST_CREATE_DTO":
            return { ...state, languageTestCreateDto: action.payload };
        case "SET_LANGUAGE_TEST_UPDATE_DTO":
            return { ...state, languageTestUpdateDto: action.payload };
        case "SET_CAREER_DESCRIPTION_CREATE_DTO":
            return { ...state, careerDescriptionCreateDto: action.payload };
        case "SET_CAREER_DESCRIPTION_UPDATE_DTO":
            return { ...state, careerDescriptionUpdateDto: action.payload };
        case "SET_COVER_LETTER_CREATE_DTO":
            return { ...state, coverLetterCreateDto: action.payload };
        case "SET_COVER_LETTER_UPDATE_DTO":
            return { ...state, coverLetterUpdateDto: action.payload };
        case "SET_PROPOSAL_SUMMARY_FOR_MEMBER_DTOS":
            return { ...state, proposalSummaryForMemberDtos: action.payload };
        case "SET_PROPOSAL_RESPONSE_DTO":
            return { ...state, proposalResponseDto: action.payload };
        case "SET_PROPOSAL_UPDATE_DTO":
            return { ...state, proposalUpdateDto: action.payload };
        case "SET_APPLICATION_SUMMARY_FOR_MEMBER_DTOS":
            return { ...state, applicationSummaryForMemberDtos: action.payload };
        case "SET_APPLICATION_RESPONSE_DTO":
            return { ...state, applicationResponseDto: action.payload };
        case "SET_PAYMENT_RESPONSE_DTOS":
            return { ...state, paymentResponseDtos: action.payload };
        default:
            return state;
    }
};
