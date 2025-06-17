
import type { MemberMyPageResponseDto, MyPageUpdateDto, MemberUpdateDto, PasswordUpdateDto } from "../../props/myPageForMemberProps";



export interface MemberInfoTabState {
    memberMyPageResponseDto: MemberMyPageResponseDto;
    showMemberInfoUpdateModal : boolean;
    showMemberInfoUpdateModalTab : "기본정보" | "닉네임" | "비밀번호";
    myPageUpdateDto : MyPageUpdateDto;
    memberUpdateDto : MemberUpdateDto;
    passwordUpdateDto : PasswordUpdateDto;
}


export type MemberInfoTabAction =
    | { type: "SET_MEMBER_MY_PAGE_RESPONSE_DTO"; payload: MemberMyPageResponseDto }
    | { type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL"; payload: boolean }
    | { type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL_TAB"; payload: "기본정보" | "닉네임" | "비밀번호" }
    | { type: "SET_MY_PAGE_UPDATE_DTO"; payload: MyPageUpdateDto }
    | { type: "SET_MEMBER_UPDATE_DTO"; payload: MemberUpdateDto }
    | { type: "SET_PASSWORD_UPDATE_DTO"; payload: PasswordUpdateDto };
