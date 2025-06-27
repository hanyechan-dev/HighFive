import { Navigate, Route, Routes } from "react-router-dom";
import { JobPostingForMemberPageProvider } from "../jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import { RequestPageProvider } from "../request/contexts/RequestPageProvider";
import { MyPageForMemberPageProvider } from "../myPageForMember/contexts/MyPageForMemberPage/MyPageForMemberPagePageProvider";
import Layout from "../layout/Layout";

import JobPostingForMemberPage from "../jobPostingForMember/pages/JobPostingForMemberPage"
import FeedbackRequestPage from "../request/pages/FeedbackRequestPage"
import EditRequestPage from "../request/pages/EditRequestPage"
import MyPageForMemberPage from "../myPageForMember/pages/MyPageForMemberPage"
import SubscriptionPlansForMemberPage from "../subscriptionForMember/pages/SubscriptionPlansForMemberPage";
import PostPage from "../post/pages/PostPage";
import SubscriptionManagementForMemberPage from "../subscriptionForMember/pages/SubscriptionManagementForMemberPage";
import MainPage from "../../common/pages/MainPage";
import LoginProtectedRouter from "./LoginProtectedRouter";
import SubscriptionProtectedRouter from "./SubscriptionProtectedRouter";

interface MemberRouterProps {
    isLogin: boolean | null
    userType: string
    isSubscribe: boolean | null
}

const MemberRouter = ({ isLogin, userType, isSubscribe }: MemberRouterProps) => {
    return (
        <Routes>
            <Route element={<Layout userType={userType} isLogin={isLogin} />}>

                <Route element={<LoginProtectedRouter isLogin={isLogin} />}>

                    <Route element={<SubscriptionProtectedRouter isSubscribe={isSubscribe} />}>

                        <Route
                            path="/job-postings"
                            element={
                                <JobPostingForMemberPageProvider>
                                    <JobPostingForMemberPage />
                                </JobPostingForMemberPageProvider>
                            }
                        />

                        <Route
                            path="/feedbacks"
                            element={
                                <RequestPageProvider>
                                    <FeedbackRequestPage />
                                </RequestPageProvider>
                            }
                        />

                        <Route
                            path="/edits"
                            element={
                                <RequestPageProvider>
                                    <EditRequestPage />
                                </RequestPageProvider>
                            }
                        />

                        <Route
                            path="/subscription"
                            element={<SubscriptionManagementForMemberPage />
                            }
                        />

                    </Route>

                    <Route
                        path="/subscription/plans"
                        element={<SubscriptionPlansForMemberPage />}
                    />

                    <Route
                        path="/my"
                        element={
                            <MyPageForMemberPageProvider>
                                <MyPageForMemberPage />
                            </MyPageForMemberPageProvider>
                        }
                    />

                </Route>

                <Route path="/" element={<MainPage />} />

                <Route
                    path="/community"
                    element={<PostPage />}
                />
            </Route>

        </Routes >
    );
};

export default MemberRouter;