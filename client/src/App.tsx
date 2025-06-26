import { useEffect } from "react";
import AppRouter from "./features/AppRoutes";
import MyPage from "./features/companyInfo/pages/MyPage";
import MainPage from "./common/pages/MainPage";








function App() {


    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지

    // const onClick = async () => {
    //     const res = await api(true).post("/payments", {
    //         paymentAmount: 10000,
    //         content: "카카오페이 테스트",
    //         method: "카카오페이"
    //     })
    //     console.log(res)

    // }



    return (
        <>
            <MainPage />
        </>
    )

}

export default App
