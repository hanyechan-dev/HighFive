import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonPage from "../../../common/pages/CommonPage";
import MyPageTap from "../components/MyPageTap";
import { ApplicationTabProvider } from "../contexts/ApplicationTab/ApplicationTabProvider";
import { DocumentTabProvider } from "../contexts/DocumentTab/DocumentTabProvider";
import { MemberInfoTabProvider } from "../contexts/MemberInfoTab/MemberInfoTabProvider";
import { ProposalTapProvider } from "../contexts/ProposalTap/ProposalTapProvider";
import { useMyPageForMemberPageController } from "../customHooks/MyPageForMemberPage/useMyPageForMemberPageController";
import ApplicationTab from "../tabs/applicationTap/ApplicationTab";



import DocumentTab from "../tabs/documentTab/DocumentTab";
import MemberInfoTab from "../tabs/memberInfoTabs/MemberInfoTab";
import ProposalTab from "../tabs/proposalTab/ProposalTab";

const myPageTabList = ["회원정보", "이력서/경력기술서/자기소개서", "채용제안", "지원내역", "결제내역"];




const MyPageForMemberPage = () => {

    const {
        showMyPageTab,
        setShowMyPageTab,
    } = useMyPageForMemberPageController()

    return (
        <div>
            <CommonPage>
                <ModalTitle title={`${showMyPageTab} 관리`} />
                <div className="flex">
                    <MyPageTap textList={myPageTabList} checkedText={showMyPageTab} setCheckedText={setShowMyPageTab} />

                    {showMyPageTab == "회원정보" &&
                        <MemberInfoTabProvider>
                            <MemberInfoTab />
                        </MemberInfoTabProvider>
                    }

                    {showMyPageTab == "이력서/경력기술서/자기소개서" &&
                        <DocumentTabProvider>
                            <DocumentTab />
                        </DocumentTabProvider>
                    }

                    {showMyPageTab == "채용제안" &&
                        <ProposalTapProvider>
                            <ProposalTab />
                        </ProposalTapProvider>
                    }
                    {showMyPageTab == "지원내역" &&
                        <ApplicationTabProvider>
                            <ApplicationTab />
                        </ApplicationTabProvider>
                    }
                </div>
            </CommonPage>
        </div>
    );
};

export default MyPageForMemberPage;


