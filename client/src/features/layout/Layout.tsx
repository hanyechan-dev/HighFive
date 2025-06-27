import { Outlet } from "react-router-dom"
import Header from "../header/Header"
import NavigationBar from "../navigationBar/NavigationBar"
import Footer from "../footer/Footer";


interface LayoutProps {
    isLogin : boolean | null
    userType: string;
}

const Layout = ({userType,isLogin}:LayoutProps) => {


    return (
        <>
            <Header isLogin={isLogin} />
            <NavigationBar userType={userType} />
            <div className="mt-6">
                <Outlet />
            </div>
            <Footer />
        </>
    )

}

export default Layout