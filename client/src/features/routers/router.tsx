import { lazy, Suspense } from "react";
import { createBrowserRouter, Outlet } from "react-router-dom";
import Header from "../header/Header";
import NavigationBar from "../navigationBar/NavigationBar";
import { JobPostingForMemberPageProvider } from "../jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import { RequestPageProvider } from "../request/contexts/RequestPageProvider";
import { MyPageForMemberPageProvider } from "../myPageForMember/contexts/MyPageForMemberPage/MyPageForMemberPagePageProvider";




const JobPostingForMemberPage = lazy(() => import("../jobPostingForMember/pages/JobPostingForMemberPage"));
const FeedbackRequestPage = lazy(() => import("../request/pages/FeedbackRequestPage"));
const EditRequestPage = lazy(() => import("../request/pages/EditRequestPage"));
const MyPageForMemberPage = lazy(() => import("../myPageForMember/pages/MyPageForMemberPage"));

const Loading = <div>Loading...</div>;
const Layout = () => {
    return (
        <>
            <Header />
            <NavigationBar />
            <Outlet />
        </>
    )

}

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        children: [

            {
                path: "job-postings",
                element: (
                    <Suspense fallback={Loading}>
                        <div className="ml-[210px] mt-6">
                            <JobPostingForMemberPageProvider>
                                <JobPostingForMemberPage />
                            </JobPostingForMemberPageProvider>
                        </div>
                    </Suspense>
                ),
            },
            {
                path: "feedbacks",
                element: (
                    <Suspense fallback={Loading}>
                        <div className="ml-[210px] mt-6">
                            <RequestPageProvider>
                                <FeedbackRequestPage />
                            </RequestPageProvider>
                        </div>
                    </Suspense>
                ),
            },
            {
                path: "edits",
                element: (
                    <Suspense fallback={Loading}>
                        <div className="ml-[210px] mt-6">
                            <RequestPageProvider>
                                <EditRequestPage />
                            </RequestPageProvider>
                        </div>
                    </Suspense>
                ),
            },
            {
                path: "my",
                element: (
                    <Suspense fallback={Loading}>
                        <div className="ml-[210px] mt-6">
                            <MyPageForMemberPageProvider>
                                <MyPageForMemberPage />
                            </MyPageForMemberPageProvider>
                        </div>
                    </Suspense>
                ),
            },
        ],
    },
]);

export default router;