
import { useEffect } from "react";

import CoverLetterInfo from "./common/components/coverLetter/CoverLetterInfo";
import type { coverLetterProps, aiContentsProps, careerProps } from "./common/props/AiConsultingProps";
import AiConsultingFeedbackDetailModal from "./common/modals/AiConsultingEditDetailModal";
import ConsultingRequestPage from "./common/pages/ConsultingRequestPage";
import ResumeOutputModal from "./features/request/resumeOutputModal";











function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        <>
            <ResumeOutputModal />


        </>
    )

}

export default App
