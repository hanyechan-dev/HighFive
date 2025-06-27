import { useDispatch } from "react-redux";
import { openAuthModal } from "../../common/slices/AuthModalSlice";
import { Outlet } from "react-router-dom";

interface LoginProtectedRouterProps{
    isLogin : boolean | null
}

const LoginProtectedRouter = ({isLogin }: LoginProtectedRouterProps) => {
    const dispatch = useDispatch();

    if (!isLogin) {
        const result = window.confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?");
        if (result) {
            dispatch(openAuthModal());
        }
        else {
            return null;
        }
    }

    return <Outlet />;

};

export default LoginProtectedRouter;
