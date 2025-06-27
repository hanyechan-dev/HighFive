import { Navigate, Route, Routes } from "react-router-dom";
import Layout from "../layout/Layout";
import UserManagementPage from "../userManagement/pages/UserManagementPage";
import PostPage from "../post/pages/PostPage";
import AdminMainPage from "../admin/AdminMainPage";
import AdminService from "../admin/dashboard/AdminService";
import AdminPrompt from "../admin/dashboard/AdminPrompt";
import LoginProtectedRouter from "./LoginProtectedRouter";

interface AdminRouterProps {
    isLogin: boolean | null
    userType: string
}

const AdminRouter = ({ userType, isLogin }: AdminRouterProps) => {
    return (
        <Routes>
            <Route element={<Layout userType={userType} isLogin={isLogin} />}>

                <Route element={<LoginProtectedRouter isLogin={isLogin} />}>

                    <Route path="/" element={<Navigate to="/admin" replace />} />

                    <Route path="/admin" element={<AdminMainPage />} />

                    <Route path="/dashboard/members" element={<UserManagementPage />} />

                    {/* <Route path="/dashboard/consulting" element={<UserManagementPage />} /> */}

                    <Route path="/dashboard/service" element={<AdminService />} />

                    <Route path="/dashboard/prompt" element={<AdminPrompt />} />

                </Route>

                <Route path="/community" element={<PostPage />} />

            </Route>
        </Routes>
    );
};

export default AdminRouter;