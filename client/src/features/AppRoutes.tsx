import { useSelector } from "react-redux";
import type { RootState } from "../common/store/store";
import AuthUtil from "../common/utils/AuthUtil";
import AdminRouter from "./routers/AdminRouter";
import CommonRouter from "./routers/CommonRouter";
import CompanyRouter from "./routers/CompanyRouter";
import MemberRouter from "./routers/MemberRouter";


const AppRouter = () => {
    const accessToken = useSelector((state: RootState) => state.auth.accessToken);
    const userType = AuthUtil.getUserTypeFromToken(accessToken);
    const isSubscribe = AuthUtil.isSubscribeFromToken(accessToken);
    

    switch (userType) {
        case "일반회원":
            return <MemberRouter userType={userType} />;
        case "기업회원":
            return <CompanyRouter userType={userType} hasSubscription={isSubscribe} />;
        case "관리자":
            return <AdminRouter userType={userType} />;
        default:
            return <CommonRouter userType={"일반회원"} />;

    }
};

export default AppRouter;