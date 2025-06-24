import { useEffect } from "react";
import Button from "./common/components/button/Button";
import { api } from "./common/Axios";







function App() {


    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지

    const onClick = async () =>{
        const res = api(true).post("/payments",{
            paymentAmount : 10000,
            content : "카카오페이 테스트",
            method : "카카오페이"
        })

        window.location.href = res.data.next_redirect_pc_url;
    }



    return (
        <Button color={"theme"} size={"s"} disabled={false} text={"결제 테스트"} type={"button"} onClick={onClick} />
    )

}

export default App
