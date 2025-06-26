import { Routes, Route, Navigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { SubscriptionApi } from "./subscriptionPage/apis/SubscriptionApi";
import { printErrorInfo } from "../common/utils/ErrorUtil";
import AuthUtil from "../common/utils/AuthUtil";
import type { RootState } from "../common/store/store";

// 페이지 컴포넌트들
import SubscriptionManagementPage from "./subscriptionPage/pages/SubscriptionManagementPage";
import SubscriptionPlansPage from "./subscriptionPage/pages/SubscriptionPlansPage";
import JobPostingPage from "./jobPostingPage/pages/JobPostingPage";
import ApplicationPage from "./applicationPage/pages/ApplicationPage";
import ApplicationJobPostingPage from "./applicationPage/pages/ApplicationJobPostingPage";
import PassPage from "./passPage/pages/PassPage";
import PassJobPostingPage from "./passPage/pages/PassJobPostingPage";
import MemberPoolPage from "./memberPoolPage/pages/MemberPoolPage";
import ProposalPage from "./proposalPage/pages/ProposalPage";
import SchedulePage from "./schedulePage/pages/SchedulePage";
import MyPage from "./companyInfo/pages/MyPage";

const CompanyRoutes = () => {
    const [hasSubscription, setHasSubscription] = useState<boolean | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [userType, setUserType] = useState<string | null>(null);

    const { accessToken } = useSelector((state: RootState) => state.auth);

    useEffect(() => {
        checkUserPermission();
    }, [accessToken]);

    const checkUserPermission = async () => {
        try {
            setIsLoading(true);
            setError(null);

            // 1. 사용자 타입 확인
            const currentUserType = AuthUtil.getUserTypeFromToken(accessToken);
            setUserType(currentUserType);

            if (currentUserType !== '기업회원') {
                setError('기업회원만 접근할 수 있는 페이지입니다.');
                return;
            }

            // 2. 구독 상태 확인
            const res = await SubscriptionApi();
            setHasSubscription(!!res?.data);
        } catch (err) {
            printErrorInfo(err);
            setError('권한 정보를 불러오는 중 오류가 발생했습니다.');
            setHasSubscription(false);
        } finally {
            setIsLoading(false);
        }
    };

    // 로딩 상태
    if (isLoading) {
        return (
            <div className="flex flex-col justify-center items-center min-h-screen">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-theme mb-4"></div>
            </div>
        );
    }

    // 에러 상태
    if (error) {
        return (
            <div className="flex flex-col justify-center items-center min-h-screen">
                <div className="text-red-500 text-xl mb-4">⚠️</div>
                <p className="text-gray-700 mb-4">{error}</p>
                <button
                    onClick={checkUserPermission}
                    className="bg-theme text-white px-4 py-2 rounded-lg hover:bg-pink-600 transition-colors"
                >
                    다시 시도
                </button>
            </div>
        );
    }

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
                    <Route path="/" element={<Navigate to="/job-posting" replace />} />

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

                    {/* 마이페이지 */}
                    <Route path="/mypage" element={<MyPage />} />

                    {/* 404 페이지 */}
                    <Route path="*" element={<Navigate to="/" replace />} />
                </>
            )}
        </Routes>
    );
};

export default CompanyRoutes; 