
import type { MemberMyPageResponseDto } from "../../props/myPageForMemberProps";



export interface MemberInfoTabState {
    memberMyPageResponseDto: MemberMyPageResponseDto;
    showModal : boolean;
    showModalType : "기본정보" | "닉네임" | "비밀번호";
}


export type MemberInfoTabAction =
    | { type: "SET_MEMBER_MY_PAGE_RESPONSE_DTO"; payload: MemberMyPageResponseDto }
    | { type: "SET_SHOW_MODAL"; payload: boolean }
    | { type: "SET_SHOW_MODAL_TYPE"; payload: "기본정보" | "닉네임" | "비밀번호" }
