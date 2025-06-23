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
        }
    },
    showModal : false,
    showModalType : "기본정보",
}


export const reducer = (state: MemberInfoTabState, action: MemberInfoTabAction): MemberInfoTabState => {
    switch (action.type) {
        case "SET_MEMBER_MY_PAGE_RESPONSE_DTO":
            return {
                ...state,
                memberMyPageResponseDto: {
                    ...state.memberMyPageResponseDto,
                    ...action.payload,
                },
            };
        case "SET_SHOW_MODAL":
            return { ...state, showModal: action.payload };
        case "SET_SHOW_MODAL_TYPE":
            return { ...state, showModalType: action.payload };
        default:
            return state;
    }
};