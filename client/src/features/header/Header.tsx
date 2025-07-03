

import { useDispatch } from "react-redux";
import { clearToken } from "../auth/slices/AuthSlice";
import { CircleUserIcon } from "lucide-react";
import { Link, useNavigate } from "react-router-dom";
import { closeAuthModal, openAuthModal } from "../../common/slices/AuthModalSlice";

interface HeaderProps {
    isLogin: boolean | null
}

const Header = ({ isLogin }: HeaderProps) => {

    const navigate = useNavigate();
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
                <div className="h-[100px] flex items-center ">
                    <div className="w-[1452px] flex justify-between mx-auto">
                        <div>
                            <Link to={"/"}>
                                <img src="/jobPrize.png" alt="jobPrize" className="h-[50px]" />
                            </Link>
                        </div>
                        <div className="font-bold font-roboto cursor-pointer">
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
            </div>
        </>
    )
}

export default Header
