import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonPage from "../../../common/pages/CommonPage";
import MyPageTap from "../components/MyPageTap";
import { useMyPageForMemberPageController } from "../customHooks/useMyPageForMemberPageController";
import DocumentTab from "../tabs/DocumentTab";
import MemberInfoTab from "../tabs/MemberInfoTab";

const myPageTabList = ["회원정보", "이력서/경력기술서/자기소개서", "채용제안", "지원내역", "결제내역"];




const MyPageForMemberPage = () => {

    const {
        showMyPageTab,
        setShowMyPageTab,
        // memberMyPageResponseDto,
        // setMemberMyPageResponseDto,
        // showMemberInfoUpdateModal,
        // setShowMemberInfoUpdateModal,
        // showMemberInfoUpdateModalTap,
        // setShowMemberInfoUpdateModalTap,
        // myPageUpdateDto,
        // setMyPageUpdateDto,
        // memberUpdateDto,
        // setMemberUpdateDto,
        // passwordUpdateDto,
        // setPasswordUpdateDto,
        // resume,
        // setResume,
        // clickedCareerDescriptionId,
        // setClickedCareerDescriptionId,
        // careerDescriptionSummaryDtos,
        // setCareerDescriptionSummaryDtos,
        // careerDescriptionResponseDtos,
        // setCareerDescriptionResponseDtos,
        // clickedCoverLetterId,
        // setClickedCoverLetterId,
        // coverLetterSummaryDtos,
        // setCoverLetterSummaryDtos,
        // coverLetterResponseDtos,
        // setCoverLetterResponseDtos,
        // educationCreateDto,
        // setEducationCreateDto,
        // educationUpdateDto,
        // setEducationUpdateDto,
        // careerCreateDto,
        // setCareerCreateDto,
        // careerUpdateDto,
        // setCareerUpdateDto,
        // certificationCreateDto,
        // setCertificationCreateDto,
        // certificationUpdateDto,
        // setCertificationUpdateDto,
        // languageTestCreateDto,
        // setLanguageTestCreateDto,
        // languageTestUpdateDto,
        // setLanguageTestUpdateDto,
        // careerDescriptionCreateDto,
        // setCareerDescriptionCreateDto,
        // careerDescriptionUpdateDto,
        // setCareerDescriptionUpdateDto,
        // coverLetterCreateDto,
        // setCoverLetterCreateDto,
        // coverLetterUpdateDto,
        // setCoverLetterUpdateDto,
        // proposalSummaryForMemberDtos,
        // setProposalSummaryForMemberDtos,
        // proposalResponseDto,
        // setProposalResponseDto,
        // proposalUpdateDto,
        // setProposalUpdateDto,
        // applicationSummaryForMemberDtos,
        // setApplicationSummaryForMemberDtos,
        // applicationResponseDto,
        // setApplicationResponseDto,
        // paymentResponseDtos,
        // setPaymentResponseDtos
    } = useMyPageForMemberPageController()

    return (
        <div>
            <CommonPage>
                <ModalTitle title={"마이페이지"} />
                <div className="flex">
                    <MyPageTap textList={myPageTabList} checkedText={showMyPageTab} setCheckedText={setShowMyPageTab} />
                    {showMyPageTab=="회원정보" && <MemberInfoTab />}
                    {showMyPageTab=="이력서/경력기술서/자기소개서" && <DocumentTab />}
                </div>
            </CommonPage>
        </div>
    );
};

export default MyPageForMemberPage;


