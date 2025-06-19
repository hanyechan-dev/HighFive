import { useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import ApplicationJobPostingPage from "./company/applicationPage/pages/ApplicationJobPostingPage";
import ApplicationPage from "./company/applicationPage/pages/ApplicationPage";
import ProposalListHeader from "./company/proposalPage/components/ProposalListHeader";

function App() {
    
    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);
    
    // 상기 유즈이펙트 수정 절대 금지

    return (
        <ProposalListHeader/>
        // <Routes>
        //     <Route path="/" element={<ApplicationJobPostingPage />} />
        //     <Route path="/job-posting/:jobPostingId/applicants" element={<ApplicationPage />} />
        // </Routes>
    )
}

export default App
