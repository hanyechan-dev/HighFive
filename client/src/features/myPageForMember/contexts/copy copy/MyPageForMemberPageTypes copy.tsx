

import type { CareerDescriptionSummaryDto, CareerDescriptionResponseDto, CoverLetterSummaryDto, CoverLetterResponseDto, MemberMyPageResponseDto, MyPageUpdateDto, MemberUpdateDto, PasswordUpdateDto, CareerCreateDto, CareerUpdateDto, CertificationCreateDto, CertificationUpdateDto, EducationCreateDto, EducationUpdateDto, LanguageTestCreateDto, LanguageTestUpdateDto, CareerDescriptionCreateDto, CareerDescriptionUpdateDto, CoverLetterCreateDto, CoverLetterUpdateDto, ProposalResponseDto, ProposalSummaryForMemberDto, ProposalUpdateDto, PaymentResponseDto, ApplicationResponseDto, ApplicationSummaryForMemberDto } from "../props/myPageForMemberProps";



export interface MyPageForMemberPageState {
    showMyPageTab: "회원정보" | "이력서/경력기술서/자기소개서" | "채용제안" | "지원내역" | "결제내역";
    memberMyPageResponseDto: MemberMyPageResponseDto;
    showMemberInfoUpdateModal : boolean;
    showMemberInfoUpdateModalTab : "기본정보" | "닉네임" | "비밀번호";
    myPageUpdateDto : MyPageUpdateDto;
    memberUpdateDto : MemberUpdateDto;
    passwordUpdateDto : PasswordUpdateDto;
    showDocumentTab : "이력서" | "경력기술서" | "자기소개서";
    resume: Resume
    clickedCareerDescriptionId: number;
    careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[];
    careerDescriptionResponseDto: CareerDescriptionResponseDto;
    clickedCoverLetterId: number;
    coverLetterSummaryDtos: CoverLetterSummaryDto[];
    coverLetterResponseDto: CoverLetterResponseDto;
    educationCreateDto : EducationCreateDto;
    educationUpdateDto : EducationUpdateDto;
    careerCreateDto : CareerCreateDto;
    careerUpdateDto : CareerUpdateDto;
    certificationCreateDto : CertificationCreateDto;
    certificationUpdateDto : CertificationUpdateDto;
    languageTestCreateDto : LanguageTestCreateDto;
    languageTestUpdateDto : LanguageTestUpdateDto;
    careerDescriptionCreateDto : CareerDescriptionCreateDto;
    careerDescriptionUpdateDto : CareerDescriptionUpdateDto;
    coverLetterCreateDto : CoverLetterCreateDto;
    coverLetterUpdateDto : CoverLetterUpdateDto;
    proposalSummaryForMemberDtos : ProposalSummaryForMemberDto[];
    proposalResponseDto : ProposalResponseDto;
    proposalUpdateDto : ProposalUpdateDto;
    applicationSummaryForMemberDtos : ApplicationSummaryForMemberDto[];
    applicationResponseDto : ApplicationResponseDto;
    paymentResponseDtos : PaymentResponseDto[];
}


export type MyPageForMemberPageAction =
    | { type: "SET_SHOW_MY_PAGE_TAB"; payload: "회원정보" | "이력서/경력기술서/자기소개서" | "채용제안" | "지원내역" | "결제내역" }
    | { type: "SET_MEMBER_MY_PAGE_RESPONSE_DTO"; payload: MemberMyPageResponseDto }
    | { type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL"; payload: boolean }
    | { type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL_TAB"; payload: "기본정보" | "닉네임" | "비밀번호" }
    | { type: "SET_MY_PAGE_UPDATE_DTO"; payload: MyPageUpdateDto }
    | { type: "SET_MEMBER_UPDATE_DTO"; payload: MemberUpdateDto }
    | { type: "SET_PASSWORD_UPDATE_DTO"; payload: PasswordUpdateDto }
    | { type: "SET_SHOW_DOCUMENT_TAB"; payload: "이력서" | "경력기술서" | "자기소개서" }
    | { type: "SET_RESUME"; payload: Resume }
    | { type: "SET_CLICKED_CAREER_DESCRIPTION_ID"; payload: number }
    | { type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS"; payload: CareerDescriptionSummaryDto[] }
    | { type: "SET_CAREER_DESCRIPTION_RESPONSE_DTO"; payload: CareerDescriptionResponseDto }
    | { type: "SET_CLICKED_COVER_LETTER_ID"; payload: number }
    | { type: "SET_COVER_LETTER_SUMMARY_DTOS"; payload: CoverLetterSummaryDto[] }
    | { type: "SET_COVER_LETTER_RESPONSE_DTO"; payload: CoverLetterResponseDto }
    | { type: "SET_EDUCATION_CREATE_DTO"; payload: EducationCreateDto }
    | { type: "SET_EDUCATION_UPDATE_DTO"; payload: EducationUpdateDto }
    | { type: "SET_CAREER_CREATE_DTO"; payload: CareerCreateDto }
    | { type: "SET_CAREER_UPDATE_DTO"; payload: CareerUpdateDto }
    | { type: "SET_CERTIFICATION_CREATE_DTO"; payload: CertificationCreateDto }
    | { type: "SET_CERTIFICATION_UPDATE_DTO"; payload: CertificationUpdateDto }
    | { type: "SET_LANGUAGE_TEST_CREATE_DTO"; payload: LanguageTestCreateDto }
    | { type: "SET_LANGUAGE_TEST_UPDATE_DTO"; payload: LanguageTestUpdateDto }
    | { type: "SET_CAREER_DESCRIPTION_CREATE_DTO"; payload: CareerDescriptionCreateDto }
    | { type: "SET_CAREER_DESCRIPTION_UPDATE_DTO"; payload: CareerDescriptionUpdateDto }
    | { type: "SET_COVER_LETTER_CREATE_DTO"; payload: CoverLetterCreateDto }
    | { type: "SET_COVER_LETTER_UPDATE_DTO"; payload: CoverLetterUpdateDto }
    | { type: "SET_PROPOSAL_SUMMARY_FOR_MEMBER_DTOS"; payload: ProposalSummaryForMemberDto[] }
    | { type: "SET_PROPOSAL_RESPONSE_DTO"; payload: ProposalResponseDto }
    | { type: "SET_PROPOSAL_UPDATE_DTO"; payload: ProposalUpdateDto }
    | { type: "SET_APPLICATION_SUMMARY_FOR_MEMBER_DTOS"; payload: ApplicationSummaryForMemberDto[] }
    | { type: "SET_APPLICATION_RESPONSE_DTO"; payload: ApplicationResponseDto }
    | { type: "SET_PAYMENT_RESPONSE_DTOS"; payload: PaymentResponseDto[] };
