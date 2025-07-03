import { useDispatch } from "react-redux";
import { openAuthModal } from "../../common/slices/AuthModalSlice";
import { Outlet, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

interface LoginProtectedRouterProps {
    isLogin: boolean | null;
}

const LoginProtectedRouter = ({ isLogin }: LoginProtectedRouterProps) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [confirmed, setConfirmed] = useState(false);

    useEffect(() => {
        if (isLogin === false && !confirmed) {
            const result = window.confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?");
            if (result) {
                dispatch(openAuthModal());
            } else {
                navigate("/");
            }
            setConfirmed(true);
        }
    }, [isLogin, confirmed, dispatch, navigate]);

    if (isLogin === false && !confirmed) {
        return null;
    }

    if (isLogin === false && confirmed) {
        return null;
    }

    return <Outlet />;
};

export default LoginProtectedRouter;