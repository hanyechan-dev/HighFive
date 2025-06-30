import { useDispatch, useSelector } from "react-redux";
import type { RootState } from "../common/store/store";
import AuthUtil from "../common/utils/AuthUtil";
import AdminRouter from "./routers/AdminRouter";
import CompanyRouter from "./routers/CompanyRouter";
import { closeAuthModal } from "../common/slices/AuthModalSlice";
import { AuthProvider } from "./auth/contexts/AuthProvider";
import AuthModal from "./auth/modals/AuthModal";
import MemberRouter from "./routers/memberRouter";


const AppRouter = () => {

    const showAuthModal = useSelector((state: RootState) => state.authModal.showAuthModal);
    const dispatch = useDispatch();
    const accessToken = useSelector((state: RootState) => state.auth.accessToken);
    const isLogin = !!accessToken;
    const userType = AuthUtil.getUserTypeFromToken(accessToken);
    const isSubscribe = AuthUtil.isSubscribeFromToken(accessToken);


    return (
        <>
            {showAuthModal && (
                <AuthProvider>
                    <AuthModal onClose={() => dispatch(closeAuthModal())} />
                </AuthProvider>
            )}

            {userType === "일반회원" && (
                <MemberRouter userType={userType} isSubscribe={isSubscribe} isLogin={isLogin} />
            )}
            {userType === "기업회원" && (
                <CompanyRouter userType={userType} isSubscribe={isSubscribe} isLogin={isLogin} />
            )}
            {userType === "관리자" && (
                <AdminRouter userType={userType} isLogin={isLogin} />
            )}
            {!userType && (
                <MemberRouter userType={"일반회원"} isSubscribe={isSubscribe} isLogin={isLogin} />
            )}
        </>
    );
};

export default AppRouter;