import { Routes, Route, Navigate } from "react-router-dom";

// 페이지 컴포넌트들
import SubscriptionManagementPage from "../subscription/pages/SubscriptionManagementPage";
import SubscriptionPlansPage from "../subscription/pages/SubscriptionPlansPage";
import JobPostingPage from "../jobPosting/pages/JobPostingPage";
import ApplicationPage from "../application/pages/ApplicationPage";
import ApplicationJobPostingPage from "../application/pages/ApplicationJobPostingPage";
import PassPage from "../pass/pages/PassPage";
import PassJobPostingPage from "../pass/pages/PassJobPostingPage";
import MemberPoolPage from "../memberPool/pages/MemberPoolPage";
import ProposalPage from "../proposal/pages/ProposalPage";
import SchedulePage from "../schedule/pages/SchedulePage";
import MyPage from "../companyInfo/pages/MyPage";
import Layout from "../layout/Layout";
import PostPage from "../post/pages/PostPage";
import LoginProtectedRouter from "./LoginProtectedRouter";
import SubscriptionProtectedRouter from "./SubscriptionProtectedRouter";
import MainPage from "../../common/pages/MainPage";

interface CompanyRouterProps {
    isLogin: boolean | null
    userType: string
    isSubscribe: boolean | null
}

const CompanyRouter = ({ isLogin, userType, isSubscribe }: CompanyRouterProps) => {

    return (
        <Routes>
            <Route element={<Layout userType={userType} isLogin={isLogin} />}>

                <Route element={<LoginProtectedRouter isLogin={isLogin} />}>

                    <Route element={<SubscriptionProtectedRouter isSubscribe={isSubscribe} />}>

                        <Route path="/member-pool" element={<MemberPoolPage />} />

                        <Route path="/proposal" element={<ProposalPage />} />

                        <Route path="/job-postings/management" element={<JobPostingPage />} />

                        <Route path="/job-postings/applications" element={<ApplicationJobPostingPage />} />

                        <Route path="/job-postings/:jobPostingId/applications" element={<ApplicationPage />} />

                        <Route path="/job-postings/passes" element={<PassJobPostingPage />} />

                        <Route path="/job-postings/:jobPostingId/passes" element={<PassPage />} />

                        <Route path="/schedule" element={<SchedulePage />} />

                        <Route path="/subscription" element={<SubscriptionManagementPage />} />

                    </Route>

                    <Route
                        path="/subscription/plans"
                        element={
                            isSubscribe
                                ? <Navigate to="/subscription" replace />
                                : <SubscriptionPlansPage />
                        }
                    />

                    <Route path="/community" element={<PostPage />} />

                    <Route path="/my" element={<MyPage />} />

                </Route>

                <Route path="/" element={<MainPage />} />

            </Route>
        </Routes>
    );
};

export default CompanyRouter; 