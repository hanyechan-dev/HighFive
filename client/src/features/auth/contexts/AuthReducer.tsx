import type { AuthAction, AuthState } from "./AuthTypes";

export const initialState: AuthState = {
    showModalType : "logIn",
    isKakao : false,
    kakaoEmail : "",
    userType : "일반회원",
}


export const reducer = (state: AuthState, action: AuthAction): AuthState => {
    switch (action.type) {
        case "SET_SHOW_MODAL_TYPE":
            return { ...state, showModalType: action.payload };
        case "SET_IS_KAKAO":
            return { ...state, isKakao: action.payload };
        case "SET_KAKAO_EMAIL":
            return { ...state, kakaoEmail: action.payload };
        case "SET_USER_TYPE":
            return { ...state, userType: action.payload };
        default:
            return state;
    }
};
