import { Outlet } from "react-router-dom"
import Header from "../header/Header"
import NavigationBar from "../navigationBar/NavigationBar"
import Footer from "../footer/Footer";


interface LayoutProps {
    isLogin: boolean | null
    userType: string;
}

const Layout = ({ userType, isLogin }: LayoutProps) => {


    return (
        <>
            <div className="flex flex-col min-h-screen">
                <Header isLogin={isLogin} />
                <NavigationBar userType={userType} />
                <main className="flex-1 mt-6">
                    <Outlet />
                </main>
                <Footer />
            </div >

        </>
    )

}

export default Layout