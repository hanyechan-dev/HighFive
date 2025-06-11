
import { useEffect } from "react";
import ConsultingRequestPage from "./common/pages/ConsultingRequestPage";
import AiConsultingDetailModal from "./common/modals/AiConsultingDetailModal";



function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지


    return (
        <>

            <AiConsultingDetailModal />



        </>
    )

}

export default App
