
import AdminMainPage from './features/admin/AdminMainPage';
import SignUpModal from './features/auth/SignUpModal';
import AdminService from './features/admin/dashboard/AdminService';
import { Route, Routes } from 'react-router-dom';
import DashboardLayout from './features/admin/DashboardLayout';
import LoginModal from './features/auth/LoginModal';
import { useSelector } from 'react-redux';
import type { RootState } from './common/store/store';
import { connectWebSocket } from './chat/StompClient';
import { useState } from 'react';
import TempPage from './chat/TempPage';

const App = () => {
    const token = useSelector((state: RootState) => state.auth.accessToken);

    if(token){
        connectWebSocket(token);
    }

    return (
        <TempPage />
        // <Routes>
        //     {/* 어드민 페이지 */}
        //     <Route path="/admin" element={<AdminMainPage />} />
        //     <Route path="/dashboard" element={<DashboardLayout />}>
        //         <Route path="service" element={<AdminService />} />
        //     </Route>
        // </Routes>
    );
};

export default App;