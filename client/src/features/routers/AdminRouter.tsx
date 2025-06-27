import { Navigate, Route, Routes } from "react-router-dom";
import Layout from "../layout/Layout";
import AdminPage from "../userManagement/pages/AdminPage";
import UserManagementPage from "../userManagement/pages/UserManagementPage";
import PostPage from "../post/pages/PostPage";

interface AdminRouterProps {
    isLogin: boolean | null
    userType: string
}

const AdminRouter = ({ userType, isLogin }: AdminRouterProps) => {
    return (
        <Routes>
            <Route element={<Layout userType={userType} isLogin={isLogin} />}>

                <Route path="/" element={<Navigate to="/admin" replace />} />

                <Route
                    path="/admin"
                    element={<AdminPage />}
                />

                <Route
                    path="/admin/users"
                    element={<UserManagementPage />}
                />

                <Route
                    path="/community"
                    element={<PostPage />}
                />

            </Route>
        </Routes>
    );
};

export default AdminRouter;