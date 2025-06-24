import { Outlet } from "react-router-dom"
import Header from "../header/Header"
import NavigationBar from "../navigationBar/NavigationBar"
import AuthUtil from "../../common/utils/AuthUtil";
import { useSelector } from "react-redux";
import type { RootState } from "../../common/store/store";

interface LayoutProps {
    userType: string;
}

const Layout = ({userType}:LayoutProps) => {

    // const accessToken = useSelector((state : RootState) => state.auth.accessToken);
    // let userType = AuthUtil.getUserTypeFromToken(accessToken)
    // userType = userType??"일반회원";

    return (
        <>
            <Header />
            <NavigationBar userType={userType} />
            <div className="ml-[210px] mt-6">
                <Outlet />
            </div>
        </>
    )

}

export default Layout