import { useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import MemberPoolPage from "./company/memberPoolPage/pages/MemberPoolPage";
import JobPostingPage from "./company/jobPostingPage/pages/JobPostingPage";
import SubscriptionManagementPage from "./company/subscriptionPage/pages/SubscriptionManagementPage";
import SubscriptionPlansPage from "./company/subscriptionPage/pages/SubscriptionPlansPage";
import CompanyInfoInputModal from "./company/companyInfo/modals/CompanyInfoInputModal.tsx";
import CompanyInfoTab from "./company/companyInfo/pages/CompanyInfoTab.tsx";
import MyPage from "./company/companyInfo/pages/MyPage.tsx";

function App() {
    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);
    
    // 상기 유즈이펙트 수정 절대 금지

    return (
         <Routes>
            
                {/* 테스트용 - 원하는 페이지 하나만 보기 */}
            {/* <Route path="/" element={<MemberPoolPage />} /> */}
            <Route path="/" element={<MyPage/>}/>
            {/* 또는 다른 페이지들 */}
            {/* <Route path="/" element={<JobPostingPage />} /> */}
            {/* <Route path="/" element={<SubscriptionManagementPage />} /> */}
            {/* <Route path="/" element={<SubscriptionPlansPage />} />
             */}
            {/* 나중에 실제 서비스할 때는 CompanyRoutes 사용 */}
            {/* <Route path="/*" element={<CompanyRoutes />} /> */}
            
           
         </Routes>
    )
}

export default App
