import { Navigate, Route, Routes } from "react-router-dom";
import Layout from "../layout/Layout";
import AdminPage from "../userManagement/pages/AdminPage";
import UserManagementPage from "../userManagement/pages/UserManagementPage";

interface AdminRouterProps {
    userType: string
}

const AdminRouter = ({ userType }: AdminRouterProps) => {
    return (
        <Routes>
            <Route element={<Layout userType={userType} />}>
                <Route path="/" element={<Navigate to="/admin" replace />} />
                <Route
                    path="/admin"
                    element={<AdminPage />}
                />
                <Route
                    path="/admin/users"
                    element={<UserManagementPage />}
                />
            </Route>
        </Routes>
    );
};

export default AdminRouter;