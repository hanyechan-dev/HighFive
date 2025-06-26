import { Navigate, Route, Routes } from "react-router-dom";
import { JobPostingForMemberPageProvider } from "../jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import { RequestPageProvider } from "../request/contexts/RequestPageProvider";
import { MyPageForMemberPageProvider } from "../myPageForMember/contexts/MyPageForMemberPage/MyPageForMemberPagePageProvider";
import Layout from "../layout/Layout";

import JobPostingForMemberPage from "../jobPostingForMember/pages/JobPostingForMemberPage"
import FeedbackRequestPage from "../request/pages/FeedbackRequestPage"
import EditRequestPage from "../request/pages/EditRequestPage"
import MyPageForMemberPage from "../myPageForMember/pages/MyPageForMemberPage"
import SubscriptionPlansForMemberPage from "../subscription/pages/SubscriptionPlansForMemberPage";
import PostPage from "../post/pages/PostPage";

interface MemberRouterProps {
    userType: string
}

const MemberRouter = ({ userType }: MemberRouterProps) => {
    return (
        <Routes>
            <Route element={<Layout userType={userType} />}>
                <Route path="/" element={<Navigate to="/job-postings" replace />} />
                <Route
                    path="/job-postings"
                    element={<JobPostingForMemberPageProvider>
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
                    path="/subscriptions"
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
                <Route
                    path="/community"
                    element={ <PostPage />}
                />
            </Route>
        </Routes>
    );
};

export default MemberRouter;