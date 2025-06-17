import { useEffect } from "react";
import JobPostingForMemberPage from "./features/jobPostingForMember/pages/JobPostingForMemberPage";
import { JobPostingForMemberPageProvider } from "./features/jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import MyPageForMemberTap from "./features/myPageForMember/components/MyPageForMemberTap";
import { ExternalBox } from "./common/components/box/Box";
import CommonPage from "./common/pages/CommonPage";
import MemberPoolPage from "./company/memberPoolPage/pages/MemberPoolPage";
import ProposalCreateModal from "./company/memberPoolPage/modals/proposalCreateModal";






function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        <>
            <ProposalCreateModal isOpen={true} onClose={function (): void {
                throw new Error("Function not implemented.");
            } } memberId={0} />

        </>
    )

}

export default App
