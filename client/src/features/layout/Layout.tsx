import { Outlet } from "react-router-dom"
import Header from "../header/Header"
import NavigationBar from "../navigationBar/NavigationBar"
import Footer from "../footer/Footer";


interface LayoutProps {
    userType: string;
}

const Layout = ({userType}:LayoutProps) => {


    return (
        <>
            <Header />
            <NavigationBar userType={userType} />
            <div className="mt-6">
                <Outlet />
            </div>
            <Footer />
        </>
    )

}

export default Layout