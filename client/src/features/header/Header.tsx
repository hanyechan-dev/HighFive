import { useState } from "react";
import AuthModal from "../auth/modals/AuthModal";
import { useDispatch, useSelector } from "react-redux";
import { clearToken } from "../auth/slices/AuthSlice";
import AuthUtil from "../../common/utils/AuthUtil";
import { CircleUserIcon } from "lucide-react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { AuthProvider } from "../auth/contexts/AuthProvider";
import type { RootState } from "../../common/store/store";
import { closeAuthModal, openAuthModal } from "../../common/slices/AuthModalSlice";

interface HeaderProps{
    isLogin : boolean | null
}

const Header = ({isLogin}:HeaderProps) => {

    const navigate = useNavigate();
    const showAuthModal = useSelector((state: RootState) => state.authModal.showAuthModal);
    const dispatch = useDispatch();

    const onClickLogin = () => {
        dispatch(openAuthModal())
    };

    const onClickLogout = () => {
        dispatch(clearToken());
        dispatch(closeAuthModal());
        navigate("/", { replace: true });
    };

    return (
        <>
            <div className="w-full">
                <div className="w-[1920px] h-[100px] flex justify-between items-center mx-auto">
                    <Link to={"/"}>
                    <img src="/jobPrize.png" alt="jobPrize" className="ml-[234px] h-[50px]" />
                    </Link>
                    <div className="mr-[234px] font-bold font-roboto cursor-pointer">
                        {!isLogin && <div className="hover:text-theme" onClick={onClickLogin}>로그인</div>}
                        {isLogin &&
                            <div className="flex gap-5 items-center">
                                <Link to={"/my"}>
                                    <CircleUserIcon className="hover:text-theme" size={30} />
                                </Link>
                                <div className="hover:text-theme" onClick={onClickLogout}>로그아웃</div>
                            </div>}
                    </div>
                </div>
            </div>

            {showAuthModal &&
                <AuthProvider>
                    <AuthModal onClose={() => dispatch(closeAuthModal())} />
                </AuthProvider>}
        </>
    )
}

export default Header
