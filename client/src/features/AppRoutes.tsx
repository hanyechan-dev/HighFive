import { useSelector } from "react-redux";
import type { RootState } from "../common/store/store";
import AuthUtil from "../common/utils/AuthUtil";
import { Navigate } from "react-router-dom";
import MemberRouter from "./routers/memberRouter";

const AppRouter = () => {
    const accessToken = useSelector((state: RootState) => state.auth.accessToken);
    const userType = AuthUtil.getUserTypeFromToken(accessToken) ?? "일반회원";

    if (!accessToken || !userType) {
        return <Navigate to="/login" replace />;
    }

    switch (userType) {
        case "일반회원":
            return <MemberRouter userType={userType} />;
        case "기업회원":
            return <CompanyRouter />;
        case "관리자":
            return <AdminRouter />;
        default:
            return <Navigate to="/login" replace />;
    }
};
  

};