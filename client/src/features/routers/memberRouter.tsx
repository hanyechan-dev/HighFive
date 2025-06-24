import { lazy } from "react";
import { Route, Routes } from "react-router-dom";
import { JobPostingForMemberPageProvider } from "../jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import { RequestPageProvider } from "../request/contexts/RequestPageProvider";
import { MyPageForMemberPageProvider } from "../myPageForMember/contexts/MyPageForMemberPage/MyPageForMemberPagePageProvider";
import Layout from "../layout/Layout";




const JobPostingForMemberPage = lazy(() => import("../jobPostingForMember/pages/JobPostingForMemberPage"));
const FeedbackRequestPage = lazy(() => import("../request/pages/FeedbackRequestPage"));
const EditRequestPage = lazy(() => import("../request/pages/EditRequestPage"));
const MyPageForMemberPage = lazy(() => import("../myPageForMember/pages/MyPageForMemberPage"));

interface MemberRouterProps{
    userType: string
}

const MemberRouter = ({userType}:MemberRouterProps) => {
    return (
            <Routes>
                <Route element={<Layout userType={userType} />}>
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
                        path="/my"
                        element={
                                <MyPageForMemberPageProvider>
                                    <MyPageForMemberPage />
                                </MyPageForMemberPageProvider>
                        }
                    />
                </Route>
            </Routes>
    );
};

export default MemberRouter;