
import AdminMainPage from './features/admin/AdminMainPage';
import SignUpModal from './features/auth/SignUpModal';
import AdminService from './features/admin/dashboard/AdminService';
import { Route, Routes, useNavigate } from 'react-router-dom';
import DashboardLayout from './features/admin/DashboardLayout';
import LoginModal from './features/auth/LoginModal';
import { useSelector } from 'react-redux';
import type { RootState } from './common/store/store';
import { connectWebSocket } from './chat/stompClient';
import { useEffect, useState } from 'react';
import TempPage from './chat/TempPage';
import Chat from './chat/Chat';

const App = () => {
    const token = useSelector((state: RootState) => state.auth.accessToken);
    const navigate = useNavigate();

    useEffect(() => {
        if(token){
            navigate('/')
            connectWebSocket(token);
        } else {
            navigate('/login')
        }
    }, [token])

    return (
        <>
            <Routes>
                <Route path="/" element={<TempPage />} />
                <Route path="/login" element={<LoginModal />} />
                {/* <Route path="/admin" element={<AdminMainPage />} />
                <Route path="/dashboard" element={<DashboardLayout />}>
                    <Route path="service" element={<AdminService />} />
                    <Route path="prompt" element={<AdminPrompt />} />
                </Route> */}
            </Routes>
             <Chat />
        </>
    );
};

export default App;