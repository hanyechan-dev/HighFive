import { lazy } from "react";
import { BrowserRouter, Outlet, Route, Routes } from "react-router-dom";
import Header from "../header/Header";
import NavigationBar from "../navigationBar/NavigationBar";
import { JobPostingForMemberPageProvider } from "../jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import { RequestPageProvider } from "../request/contexts/RequestPageProvider";
import { MyPageForMemberPageProvider } from "../myPageForMember/contexts/MyPageForMemberPage/MyPageForMemberPagePageProvider";




const JobPostingForMemberPage = lazy(() => import("../jobPostingForMember/pages/JobPostingForMemberPage"));
const FeedbackRequestPage = lazy(() => import("../request/pages/FeedbackRequestPage"));
const EditRequestPage = lazy(() => import("../request/pages/EditRequestPage"));
const MyPageForMemberPage = lazy(() => import("../myPageForMember/pages/MyPageForMemberPage"));

const Layout = () => {
    return (
        <>
            <Header />
            <NavigationBar />
            <div className="ml-[210px] mt-6">
                <Outlet />
            </div>
        </>
    )

}

const MemberRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route element={<Layout />}>
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
        </BrowserRouter>
    );
};

export default MemberRouter;