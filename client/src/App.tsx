import { useEffect } from "react";
import { RouterProvider } from "react-router-dom";
import router from "./features/routers/router";







function App() {


    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        <>
            <RouterProvider router={router} />
            
        </>
    )

}

export default App
