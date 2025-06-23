import type { MemberMyPageResponseDto } from "../../props/myPageForMemberProps";
import { useMemberInfoTabContext } from "./useMemberInfoTabContext";



export const useMemberInfoTabController = () => {
    const { state, dispatch } = useMemberInfoTabContext();

    const memberMyPageResponseDto = state.memberMyPageResponseDto;
    const setMemberMyPageResponseDto = (partial: Partial<MemberMyPageResponseDto>) => {
        dispatch({
            type: "SET_MEMBER_MY_PAGE_RESPONSE_DTO",
            payload: {
                ...state.memberMyPageResponseDto,
                ...partial,
            },
        });
    };
    const showModal = state.showModal;
    const setShowModal = (showModal: boolean) => {
        dispatch({ type: "SET_SHOW_MODAL", payload: showModal });
    };
    const showModalType = state.showModalType;
    const setShowModalType = (tab: string) => {
        if (
            tab === "기본정보" ||
            tab === "닉네임" ||
            tab === "비밀번호"
        ) {
            dispatch({ type: "SET_SHOW_MODAL_TYPE", payload: tab });
        }
    };



    return {
        memberMyPageResponseDto,
        setMemberMyPageResponseDto,
        showModal,
        setShowModal,
        showModalType,
        setShowModalType,
    };


}