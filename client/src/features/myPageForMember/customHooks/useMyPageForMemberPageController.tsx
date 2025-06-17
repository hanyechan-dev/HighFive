


import type { CareerDescriptionSummaryDto, CareerDescriptionResponseDto, CoverLetterSummaryDto, CoverLetterResponseDto, MemberMyPageResponseDto, ApplicationResponseDto, ApplicationSummaryForMemberDto, CareerCreateDto, CareerDescriptionCreateDto, CareerDescriptionUpdateDto, CareerUpdateDto, CertificationCreateDto, CertificationUpdateDto, CoverLetterCreateDto, CoverLetterUpdateDto, EducationCreateDto, EducationUpdateDto, LanguageTestCreateDto, LanguageTestUpdateDto, MemberUpdateDto, MyPageUpdateDto, PasswordUpdateDto, PaymentResponseDto, ProposalResponseDto, ProposalSummaryForMemberDto, ProposalUpdateDto } from "../../myPageForMember/props/myPageForMemberProps";
import type { Resume } from "../../jobPostingForMember/props/JobPostingForMemberProps";
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
    const memberMyPageResponseDto = state.memberMyPageResponseDto;
    const setMemberMyPageResponseDto = (memberMyPageResponseDto: MemberMyPageResponseDto) => {
        dispatch({
            type: "SET_MEMBER_MY_PAGE_RESPONSE_DTO",
            payload: memberMyPageResponseDto
        });
    }
    const showMemberInfoUpdateModal = state.showMemberInfoUpdateModal;
    const setShowMemberInfoUpdateModal = (showMemberInfoUpdateModal: boolean) => {
        dispatch({
            type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL",
            payload: showMemberInfoUpdateModal
        });
    }

    const showMemberInfoUpdateModalTab = state.showMemberInfoUpdateModalTab;
    const setShowMemberInfoUpdateModalTab = (tab: string) => {
        if (
            tab === "기본정보" ||
            tab === "닉네임" ||
            tab === "비밀번호"
        ) {
            dispatch({
                type: "SET_SHOW_MEMBER_INFO_UPDATE_MODAL_TAB",
                payload: tab
            });

        }
    }


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

    const showDocumentTab = state.showDocumentTab;
    const setShowDocumentTab = (tab: string) => {
        if (
            tab === "이력서" ||
            tab === "경력기술서" ||
            tab === "자기소개서"
        ) {
            dispatch({
                type: "SET_SHOW_DOCUMENT_TAB", payload: tab
            })
        }
    };



    const resume = state.resume;
    const setResume = (resume: Resume) => {
        dispatch({ type: "SET_RESUME", payload: resume });
    };

    const clickedCareerDescriptionId = state.clickedCareerDescriptionId;
    const setClickedCareerDescriptionId = (id: number) => {
        dispatch({ type: "SET_CLICKED_CAREER_DESCRIPTION_ID", payload: id });
    };

    const careerDescriptionSummaryDtos = state.careerDescriptionSummaryDtos;
    const setCareerDescriptionSummaryDtos = (dtos: CareerDescriptionSummaryDto[]) => {
        dispatch({ type: "SET_CAREER_DESCRIPTION_SUMMARY_DTOS", payload: dtos });
    };

    const careerDescriptionResponseDtos = state.careerDescriptionResponseDtos;
    const setCareerDescriptionResponseDtos = (dtos: CareerDescriptionResponseDto[]) => {
        dispatch({ type: "SET_CAREER_DESCRIPTION_RESPONSE_DTOS", payload: dtos });
    };

    const clickedCoverLetterId = state.clickedCoverLetterId;
    const setClickedCoverLetterId = (id: number) => {
        dispatch({ type: "SET_CLICKED_COVER_LETTER_ID", payload: id });
    };

    const coverLetterSummaryDtos = state.coverLetterSummaryDtos;
    const setCoverLetterSummaryDtos = (dtos: CoverLetterSummaryDto[]) => {
        dispatch({ type: "SET_COVER_LETTER_SUMMARY_DTOS", payload: dtos });
    };

    const coverLetterResponseDtos = state.coverLetterResponseDtos;
    const setCoverLetterResponseDtos = (dtos: CoverLetterResponseDto[]) => {
        dispatch({ type: "SET_COVER_LETTER_RESPONSE_DTOS", payload: dtos });
    };

    const educationCreateDto = state.educationCreateDto;
    const setEducationCreateDto = (dto: EducationCreateDto) => {
        dispatch({ type: "SET_EDUCATION_CREATE_DTO", payload: dto });
    };

    const educationUpdateDto = state.educationUpdateDto;
    const setEducationUpdateDto = (dto: EducationUpdateDto) => {
        dispatch({ type: "SET_EDUCATION_UPDATE_DTO", payload: dto });
    };

    const careerCreateDto = state.careerCreateDto;
    const setCareerCreateDto = (dto: CareerCreateDto) => {
        dispatch({ type: "SET_CAREER_CREATE_DTO", payload: dto });
    };

    const careerUpdateDto = state.careerUpdateDto;
    const setCareerUpdateDto = (dto: CareerUpdateDto) => {
        dispatch({ type: "SET_CAREER_UPDATE_DTO", payload: dto });
    };

    const certificationCreateDto = state.certificationCreateDto;
    const setCertificationCreateDto = (dto: CertificationCreateDto) => {
        dispatch({ type: "SET_CERTIFICATION_CREATE_DTO", payload: dto });
    };

    const certificationUpdateDto = state.certificationUpdateDto;
    const setCertificationUpdateDto = (dto: CertificationUpdateDto) => {
        dispatch({ type: "SET_CERTIFICATION_UPDATE_DTO", payload: dto });
    };

    const languageTestCreateDto = state.languageTestCreateDto;
    const setLanguageTestCreateDto = (dto: LanguageTestCreateDto) => {
        dispatch({ type: "SET_LANGUAGE_TEST_CREATE_DTO", payload: dto });
    };

    const languageTestUpdateDto = state.languageTestUpdateDto;
    const setLanguageTestUpdateDto = (dto: LanguageTestUpdateDto) => {
        dispatch({ type: "SET_LANGUAGE_TEST_UPDATE_DTO", payload: dto });
    };

    const careerDescriptionCreateDto = state.careerDescriptionCreateDto;
    const setCareerDescriptionCreateDto = (dto: CareerDescriptionCreateDto) => {
        dispatch({ type: "SET_CAREER_DESCRIPTION_CREATE_DTO", payload: dto });
    };

    const careerDescriptionUpdateDto = state.careerDescriptionUpdateDto;
    const setCareerDescriptionUpdateDto = (dto: CareerDescriptionUpdateDto) => {
        dispatch({ type: "SET_CAREER_DESCRIPTION_UPDATE_DTO", payload: dto });
    };

    const coverLetterCreateDto = state.coverLetterCreateDto;
    const setCoverLetterCreateDto = (dto: CoverLetterCreateDto) => {
        dispatch({ type: "SET_COVER_LETTER_CREATE_DTO", payload: dto });
    };

    const coverLetterUpdateDto = state.coverLetterUpdateDto;
    const setCoverLetterUpdateDto = (dto: CoverLetterUpdateDto) => {
        dispatch({ type: "SET_COVER_LETTER_UPDATE_DTO", payload: dto });
    };

    const proposalSummaryForMemberDtos = state.proposalSummaryForMemberDtos;
    const setProposalSummaryForMemberDtos = (dtos: ProposalSummaryForMemberDto[]) => {
        dispatch({ type: "SET_PROPOSAL_SUMMARY_FOR_MEMBER_DTOS", payload: dtos });
    };

    const proposalResponseDto = state.proposalResponseDto;
    const setProposalResponseDto = (dto: ProposalResponseDto) => {
        dispatch({ type: "SET_PROPOSAL_RESPONSE_DTO", payload: dto });
    };

    const proposalUpdateDto = state.proposalUpdateDto;
    const setProposalUpdateDto = (dto: ProposalUpdateDto) => {
        dispatch({ type: "SET_PROPOSAL_UPDATE_DTO", payload: dto });
    };

    const applicationSummaryForMemberDtos = state.applicationSummaryForMemberDtos;
    const setApplicationSummaryForMemberDtos = (dtos: ApplicationSummaryForMemberDto[]) => {
        dispatch({ type: "SET_APPLICATION_SUMMARY_FOR_MEMBER_DTOS", payload: dtos });
    };

    const applicationResponseDto = state.applicationResponseDto;
    const setApplicationResponseDto = (dto: ApplicationResponseDto) => {
        dispatch({ type: "SET_APPLICATION_RESPONSE_DTO", payload: dto });
    };

    const paymentResponseDtos = state.paymentResponseDtos;
    const setPaymentResponseDtos = (dtos: PaymentResponseDto[]) => {
        dispatch({ type: "SET_PAYMENT_RESPONSE_DTOS", payload: dtos });
    };



    return {
        showMyPageTab,
        setShowMyPageTab,
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
        showDocumentTab,
        setShowDocumentTab,
        resume,
        setResume,
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId,
        careerDescriptionSummaryDtos,
        setCareerDescriptionSummaryDtos,
        careerDescriptionResponseDtos,
        setCareerDescriptionResponseDtos,
        clickedCoverLetterId,
        setClickedCoverLetterId,
        coverLetterSummaryDtos,
        setCoverLetterSummaryDtos,
        coverLetterResponseDtos,
        setCoverLetterResponseDtos,
        educationCreateDto,
        setEducationCreateDto,
        educationUpdateDto,
        setEducationUpdateDto,
        careerCreateDto,
        setCareerCreateDto,
        careerUpdateDto,
        setCareerUpdateDto,
        certificationCreateDto,
        setCertificationCreateDto,
        certificationUpdateDto,
        setCertificationUpdateDto,
        languageTestCreateDto,
        setLanguageTestCreateDto,
        languageTestUpdateDto,
        setLanguageTestUpdateDto,
        careerDescriptionCreateDto,
        setCareerDescriptionCreateDto,
        careerDescriptionUpdateDto,
        setCareerDescriptionUpdateDto,
        coverLetterCreateDto,
        setCoverLetterCreateDto,
        coverLetterUpdateDto,
        setCoverLetterUpdateDto,
        proposalSummaryForMemberDtos,
        setProposalSummaryForMemberDtos,
        proposalResponseDto,
        setProposalResponseDto,
        proposalUpdateDto,
        setProposalUpdateDto,
        applicationSummaryForMemberDtos,
        setApplicationSummaryForMemberDtos,
        applicationResponseDto,
        setApplicationResponseDto,
        paymentResponseDtos,
        setPaymentResponseDtos
    };


}