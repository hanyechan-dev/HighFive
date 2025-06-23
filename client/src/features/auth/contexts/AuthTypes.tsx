

export interface AuthState {
    showModalType : "signUp" | "logIn" | "nicknameInput" | "companyInfoInput"
    isKakao : boolean
    kakaoEmail : string
    userType : "일반회원" | "기업회원" | "컨설턴트회원"
}

export type AuthAction =
    | { type: "SET_SHOW_MODAL_TYPE"; payload: "signUp" | "logIn" | "nicknameInput" | "companyInfoInput" }
    | { type: "SET_IS_KAKAO"; payload: boolean }
    | { type: "SET_KAKAO_EMAIL"; payload: string }
    | { type: "SET_USER_TYPE"; payload: "일반회원" | "기업회원" | "컨설턴트회원" }

