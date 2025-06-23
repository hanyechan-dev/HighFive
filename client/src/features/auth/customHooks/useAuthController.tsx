
import { useAuthContext } from "./useAuthContext";




export const useAuthController = () => {
    const { state, dispatch } = useAuthContext();

    const showModalType = state.showModalType;
    const setShowModalType = (showModalType: string) => {
        if (
            showModalType === "signUp" ||
            showModalType === "logIn" ||
            showModalType === "nicknameInput" ||
            showModalType === "companyInfoInput"
        ) {
            dispatch({ type: "SET_SHOW_MODAL_TYPE", payload: showModalType });
        }
    };

    const isKakao = state.isKakao;
    const setIsKakao = (isKakao: boolean) => {
        dispatch({ type: "SET_IS_KAKAO", payload: isKakao });
    };
    const kakaoEmail = state.kakaoEmail;
    const setKakaoEmail = (kakaoEmail: string) => {
        dispatch({ type: "SET_KAKAO_EMAIL", payload: kakaoEmail });
    };
    const userType = state.userType;
    const setUserType = (userType: string) => {
        if (
            userType === "일반회원" ||
            userType === "기업회원" ||
            userType === "컨설턴트회원"
        ) {
            dispatch({ type: "SET_USER_TYPE", payload: userType });
        }
    };


    return {
        showModalType,
        setShowModalType,
        isKakao,
        setIsKakao,
        kakaoEmail,
        setKakaoEmail,
        userType,
        setUserType,
    };


}