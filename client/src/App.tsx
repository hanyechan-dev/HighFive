import { useEffect } from "react";

import MyPageForMemberPage from "./features/myPageForMember/pages/MyPageForMemberPage";
import { MyPageForMemberPageProvider } from "./features/myPageForMember/contexts/MyPageForMemberPagePageProvider";






function App() {


    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        <>
            <MyPageForMemberPageProvider>
                <MyPageForMemberPage/>
            </MyPageForMemberPageProvider>
        </>
    )

}

export default App
