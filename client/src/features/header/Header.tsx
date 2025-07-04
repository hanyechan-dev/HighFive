

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
                <div className="h-[100px] flex">
                    <div className="w-[1452px] flex justify-between mx-auto items-center">
                        <div>
                            <Link to={"/"}>
                                <div className="flex justify-center items-center select-none">
                                    <span
                                        className="inline-flex items-center justify-center mr-1"
                                        style={{
                                            width: 50,
                                            height: 50,
                                            borderRadius: 12.5,
                                            border: '5px solid #e84fcf',
                                            background: '#fff',
                                            color: '#e84fcf',
                                            fontWeight: 800,
                                            fontSize: 35,
                                            fontFamily: 'Inter, Arial, Helvetica, sans-serif',
                                            boxSizing: 'border-box',
                                            animation: 'roll-bounce-in 1.3s cubic-bezier(0.68, -0.55, 0.27, 1.55)',
                                        }}
                                    >
                                        J
                                    </span>
                                    <span
                                        className="text-[38px] font-bold"
                                        style={{
                                            color: '#e84fcf',
                                            letterSpacing: '-2px',
                                            fontFamily: 'Inter, Arial, Helvetica, sans-serif',
                                        }}
                                    >
                                        obPrize
                                    </span>
                                </div>
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
