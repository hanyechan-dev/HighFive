import type { MyPageForMemberPageAction, MyPageForMemberPageState } from "./MyPageForMemberPageTypes";

export const initialState: MyPageForMemberPageState = {
    showMyPageTab: "회원정보",
}


export const reducer = (state: MyPageForMemberPageState, action: MyPageForMemberPageAction): MyPageForMemberPageState => {
    switch (action.type) {
        case "SET_SHOW_MY_PAGE_TAB":
            return { ...state, showMyPageTab: action.payload };
        default:
            return state;
    }
};
