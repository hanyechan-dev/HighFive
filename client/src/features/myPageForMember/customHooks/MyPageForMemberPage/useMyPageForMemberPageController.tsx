import { useMyPageForMemberPageContext } from "./useMyPageForMemberPageContext";



export const useMyPageForMemberPageController = () => {
    const { state, dispatch } = useMyPageForMemberPageContext();

    const showMyPageTab = state.showMyPageTab;
    const setShowMyPageTab = (tab: string) => {
        if (
            tab === "회원정보" ||
            tab === "이력서/경력기술서/자기소개서" ||
            tab === "채용제안" ||
            tab === "지원내역" ||
            tab === "결제내역"
        ) {
            dispatch({ type: "SET_SHOW_MY_PAGE_TAB", payload: tab });
        }
    };
    



    return {
        showMyPageTab,
        setShowMyPageTab,
    };


}