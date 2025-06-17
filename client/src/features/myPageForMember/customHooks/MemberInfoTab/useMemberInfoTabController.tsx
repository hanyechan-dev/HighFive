import type { MemberMyPageResponseDto, MemberUpdateDto, MyPageUpdateDto, PasswordUpdateDto } from "../../props/myPageForMemberProps";
import { useMemberInfoTabContext } from "./useMemberInfoTabContext";



export const useMemberInfoTabController = () => {
    const { state, dispatch } = useMemberInfoTabContext();

    const memberMyPageResponseDto = state.memberMyPageResponseDto;
    const setMemberMyPageResponseDto = (memberMyPageResponseDto: MemberMyPageResponseDto) => {
        dispatch({ type: "SET_MEMBER_MY_PAGE_RESPONSE_DTO", payload: memberMyPageResponseDto });
    };
    const showMemberInfoUpdateModal = state.showMemberInfoUpdateModal;
    const setShowMemberInfoUpdateModal = (showMemberInfoUpdateModal: boolean) => {
        dispatch({ type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL", payload: showMemberInfoUpdateModal });
    };
    const showMemberInfoUpdateModalTab = state.showMemberInfoUpdateModalTab;
    const setShowMemberInfoUpdateModalTab = (tab: string) => {
        if (
            tab === "기본정보" ||
            tab === "닉네임" ||
            tab === "비밀번호"
        ) {
            dispatch({ type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL_TAB", payload: tab });
        }
    };
    const myPageUpdateDto = state.myPageUpdateDto;
    const setMyPageUpdateDto = (myPageUpdateDto: MyPageUpdateDto) => {
        dispatch({ type: "SET_MY_PAGE_UPDATE_DTO", payload: myPageUpdateDto });
    };
    const memberUpdateDto = state.memberUpdateDto;
    const setMemberUpdateDto = (memberUpdateDto: MemberUpdateDto) => {
        dispatch({ type: "SET_MEMBER_UPDATE_DTO", payload: memberUpdateDto });
    };
    const passwordUpdateDto = state.passwordUpdateDto;
    const setPasswordUpdateDto = (passwordUpdateDto: PasswordUpdateDto) => {
        dispatch({ type: "SET_PASSWORD_UPDATE_DTO", payload: passwordUpdateDto });
    };



    return {
        memberMyPageResponseDto,
        setMemberMyPageResponseDto,
        showMemberInfoUpdateModal,
        setShowMemberInfoUpdateModal,
        showMemberInfoUpdateModalTab,
        setShowMemberInfoUpdateModalTab,
        myPageUpdateDto,
        setMyPageUpdateDto,
        memberUpdateDto,
        setMemberUpdateDto,
        passwordUpdateDto,
        setPasswordUpdateDto,
    };


}