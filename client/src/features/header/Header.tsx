import { useState } from "react";
import AuthModal from "../auth/modals/AuthModal";
import { useDispatch, useSelector } from "react-redux";
import { clearToken } from "../auth/slices/AuthSlice";
import AuthUtil from "../../common/utils/AuthUtil";
import { CircleUserIcon } from "lucide-react";
import { Link } from "react-router-dom";
import { AuthProvider } from "../auth/contexts/AuthProvider";



const Header = () => {
    const accessToken = useSelector((state: any) => state.auth.accessToken);
    const isLogin = AuthUtil.isLogin(accessToken);
    const dispatch = useDispatch();
    const [showAuthModal, setShowAuthModal] = useState(false);

    const onClickLogin = () => {
        setShowAuthModal(true);
    };

    const onClickLogout = () => {
        dispatch(clearToken());
        setShowAuthModal(false);
    };

    return (
        <>
            <div className="w-full">
                <div className="w-[1920px] h-[100px] flex justify-between items-center mx-auto">
                    <img src="/jobPrize.png" alt="jobPrize" className="ml-[234px] h-[50px]" />
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
                    <AuthModal onClose={() => setShowAuthModal(false)} />
                </AuthProvider>}
        </>
    )
}

export default Header
