import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonPage from "../../../common/pages/CommonPage";
import MyPageTap from "../components/MyPageTap";
import { useMyPageForMemberPageController } from "../customHooks/useMyPageForMemberPageController";
import DocumentTab from "../tabs/DocumentTab";
import MemberInfoTab from "../tabs/memberInfoTabs/MemberInfoTab";

const myPageTabList = ["회원정보", "이력서/경력기술서/자기소개서", "채용제안", "지원내역", "결제내역"];




const MyPageForMemberPage = () => {

    const {
        showMyPageTab,
        setShowMyPageTab,
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


