import { useSelector } from "react-redux";
import type { RootState } from "../common/store/store";
import AuthUtil from "../common/utils/AuthUtil";
import MemberRouter from "./routers/memberRouter";
import AdminRouter from "./routers/AdminRouter";
import CommonRouter from "./routers/CommonRouter";

const AppRouter = () => {
    const accessToken = useSelector((state: RootState) => state.auth.accessToken);
    const userType = AuthUtil.getUserTypeFromToken(accessToken);

    switch (userType) {
        case "일반회원":
            return <MemberRouter userType={userType} />;
        // case "기업회원":
        //     return <CompanyRouter />;
        case "관리자":
            return <AdminRouter userType={userType} />;
        default:
            return <CommonRouter userType={"일반회원"} />;
        
    }
};

export default AppRouter;