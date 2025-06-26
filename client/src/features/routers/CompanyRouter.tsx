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

interface CompanyRouterProps {
    userType: string
    hasSubscription: boolean | null
}

const CompanyRouter = ({ userType, hasSubscription }: CompanyRouterProps) => {

    // const [isLoading, setIsLoading] = useState(true);
    // const [error, setError] = useState<string | null>(null);

    // 로딩 상태
    // if (isLoading) {
    //     return (
    //         <div className="flex flex-col justify-center items-center min-h-screen">
    //             <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-theme mb-4"></div>
    //         </div>
    //     );
    // }


    // 기업회원이 아닌 경우
    if (userType !== '기업회원') {
        return (
            <div className="flex flex-col justify-center items-center min-h-screen">
                <div className="text-red-500 text-xl mb-4">🚫</div>
                <p className="text-gray-700 mb-4">기업회원만 접근할 수 있는 페이지입니다.</p>
                <p className="text-gray-500 text-sm">일반회원으로 로그인되어 있습니다.</p>
            </div>
        );
    }

    return (
        <Routes>
            <Route element={<Layout userType={userType} />}>
                {/* 구독이 없으면 구독 플랜 페이지로 리다이렉트 */}
                {!hasSubscription ? (
                    <>
                        <Route path="/" element={<Navigate to="/subscription/plans" replace />} />
                        <Route path="/subscription/plans" element={<SubscriptionPlansPage />} />
                        <Route path="*" element={<Navigate to="/subscription/plans" replace />} />
                    </>
                ) : (
                    <>
                        {/* 구독이 있으면 모든 기능 사용 가능 */}
                        <Route path="/" element={<Navigate to="/member-pool" replace />} />

                        {/* 구독 관련 페이지 */}
                        <Route path="/subscription" element={<SubscriptionManagementPage />} />
                        <Route path="/subscription/plans" element={<SubscriptionPlansPage />} />

                        {/* 채용공고 관리 */}
                        <Route path="/job-postings/management" element={<JobPostingPage />} />

                        {/* 지원자 관리 */}
                        <Route path="/job-postings/applications" element={<ApplicationJobPostingPage />} />
                        <Route path="/job-postings/:jobPostingId/applications" element={<ApplicationPage />} />

                        {/* 합격자 관리 */}
                        <Route path="/job-postings/passes" element={<PassJobPostingPage />} />
                        <Route path="/job-postings/:jobPostingId/passes" element={<PassPage />} />

                        {/* 인재풀 */}
                        <Route path="/member-pool" element={<MemberPoolPage />} />

                        {/* 제안 관리 */}
                        <Route path="/proposal" element={<ProposalPage />} />

                        {/* 일정 관리 */}
                        <Route path="/schedule" element={<SchedulePage />} />



                        {/* 404 페이지 */}
                        <Route path="*" element={<Navigate to="/" replace />} />
                    </>
                )}
                <Route
                    path="/community"
                    element={<PostPage />}
                />
                {/* 마이페이지 */}
                <Route path="/my" element={<MyPage />} />
            </Route>
        </Routes>
    );
};

export default CompanyRouter; 