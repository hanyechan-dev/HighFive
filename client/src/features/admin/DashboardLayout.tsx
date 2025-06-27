import { Outlet } from "react-router-dom";
import Sidebar from "./dashboard/AdminSIdeBar";

const DashboardLayout = () => {
    return (
        // 사이드바를 왼쪽에 고정
        <div className="flex h-screen bg-background">
            <Sidebar />
            {/* 메인 컨텐츠 공간 생성 */}
            <main className="flex-1 overflow-y-auto p-6">
                <Outlet />
            </main>
        </div>
    );
};

export default DashboardLayout;