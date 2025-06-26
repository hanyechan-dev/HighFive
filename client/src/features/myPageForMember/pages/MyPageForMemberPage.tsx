import PageTitle from "../../../common/components/title/PageTitle";
import CommonPage from "../../../common/pages/CommonPage";
import MyPageTap from "../components/MyPageTap";
import { ApplicationTabProvider } from "../contexts/ApplicationTab/ApplicationTabProvider";
import { DocumentTabProvider } from "../contexts/DocumentTab/DocumentTabProvider";
import { MemberInfoTabProvider } from "../contexts/MemberInfoTab/MemberInfoTabProvider";
import { PaymentTabProvider } from "../contexts/PaymentTab/PaymentTabProvider";
import { ProposalTapProvider } from "../contexts/ProposalTap/ProposalTapProvider";
import { useMyPageForMemberPageController } from "../customHooks/MyPageForMemberPage/useMyPageForMemberPageController";
import ApplicationTab from "../tabs/applicationTab/ApplicationTab";



import DocumentTab from "../tabs/documentTab/DocumentTab";
import MemberInfoTab from "../tabs/memberInfoTab/MemberInfoTab";
import PaymentTab from "../tabs/paymentTab/PaymentTab";
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
                <PageTitle
                    title={`${showMyPageTab} 관리`} />
                <div className="flex mt-6">
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

                    {showMyPageTab == "결제내역" &&
                        <PaymentTabProvider>
                            <PaymentTab />
                        </PaymentTabProvider>
                    }
                </div>
            </CommonPage>
        </div>
    );
};

export default MyPageForMemberPage;


