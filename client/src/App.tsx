
import { useEffect } from "react";
import MemberFilterModal from "./company/memberPoolPage/MemberPoolFilterModal";











function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
       <MemberFilterModal isOpen={true} onClose={function (): void {
            throw new Error("Function not implemented.");
        } } />
       
            


        
    )

}

export default App
