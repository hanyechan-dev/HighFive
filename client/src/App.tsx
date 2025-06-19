
import AdminMainPage from './features/admin/AdminMainPage';
import SignUpModal from './features/auth/SignUpModal';
import AdminService from './features/admin/dashboard/AdminService';
import { Route, Routes } from 'react-router-dom';
import DashboardLayout from './features/admin/DashboardLayout';

const App = () => {
    return (
        <Routes>
            {/* 어드민 페이지 */}
            <Route path="/admin" element={<AdminMainPage />} />
            <Route path="/dashboard" element={<DashboardLayout />}>
                <Route path="service" element={<AdminService />} />
            </Route>
        </Routes>
    );
};

export default App;