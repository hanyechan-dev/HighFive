import { useEffect } from "react";
import JobPostingForMemberPage from "./features/jobPostingForMember/pages/JobPostingForMemberPage";
import { JobPostingForMemberPageProvider } from "./features/jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import MyPageForMemberTap from "./features/myPageForMember/components/MyPageForMemberTap";
import { ExternalBox } from "./common/components/box/Box";
import CommonPage from "./common/pages/CommonPage";
import MemberPoolPage from "./company/memberPoolPage/pages/MemberPoolPage";
import ProposalCreateModal from "./company/memberPoolPage/modals/proposalCreateModal";
import JobPostingDetailForMemberModal from "./features/jobPostingForMember/modals/JobPostingDetailForMemberModal";
import JobPostingPage from "./company/jobPostingPage/pages/JobPostingPage";
import ProposalPage from "./company/proposalPage/pages/ProposalPage";
import ApplicationPage from "./company/applicationPage/pages/ApplicationPage";
import ApplicationJobPostingPage from "./company/applicationPage/pages/ApplicationJobPostingPage";






function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        //<ApplicationPage/>
          <ApplicationJobPostingPage/>
       


    )

}

export default App
