import { useEffect } from "react";
import Button from "./common/components/button/Button";
import { api } from "./common/Axios";
import AppRouter from "./features/AppRoutes";
import SubscriptionPlansForMemberPage from "./features/subscription/pages/SubscriptionPlansForMemberPage";
import ProposalPage from "./company/proposalPage/pages/ProposalPage";
import SchedulePage from "./company/schedulePage/pages/SchedulePage";
import MyPage from "./company/companyInfo/pages/MyPage";
import PaymentTab from "./company/companyInfo/pages/PaymentTab";
import SubscriptionPlansPage from "./company/subscriptionPage/pages/SubscriptionPlansPage";







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
            <SubscriptionPlansPage/>
            {/* <SubscriptionPlansForMemberPage /> */}
        </>
    )

}

export default App
