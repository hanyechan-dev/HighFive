
import { useEffect } from "react";
import FeedbackRequestPage from "./features/request/FeedbackRequestPage";
import RequestDetailModal from "./features/request/RequestDetailModal";





function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        <>
            <RequestDetailModal consultingType={"피드백"} />


        </>
    )

}

export default App
