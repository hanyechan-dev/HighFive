export interface MyPageForMemberPageState {
    showMyPageTab: "회원정보" | "이력서/경력기술서/자기소개서" | "채용제안" | "지원내역" | "결제내역";
}


export type MyPageForMemberPageAction =
    | { type: "SET_SHOW_MY_PAGE_TAB"; payload: "회원정보" | "이력서/경력기술서/자기소개서" | "채용제안" | "지원내역" | "결제내역" };
