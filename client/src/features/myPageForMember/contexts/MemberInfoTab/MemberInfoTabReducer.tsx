import type { MemberInfoTabAction, MemberInfoTabState } from "./MemberInfoTabTypes";

export const initialState: MemberInfoTabState = {
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
}


export const reducer = (state: MemberInfoTabState, action: MemberInfoTabAction): MemberInfoTabState => {
    switch (action.type) {
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
        default:
            return state;
    }
};
