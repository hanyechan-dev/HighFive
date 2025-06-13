
import { useEffect, useState } from "react";
import MemberFilterModal from "./company/memberPoolPage/MemberPoolFilterModal";
import MemberDetailModal from "./company/memberPoolPage/MemberPoolDetailModal";











function App() {

    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지

    const [isMemberDetailModalOpen, setIsMemberDetailModalOpen] = useState(true); //

   
    const handleCloseMemberDetailModal = () => {
        setIsMemberDetailModalOpen(false);
    };

    return (
        
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6">인재 상세 모달 (기본 더미 데이터 확인)</h1>

            
            <MemberDetailModal
                isOpen={isMemberDetailModalOpen}        // 모달의 열림/닫힘 상태 전달
                onClose={handleCloseMemberDetailModal}  // 모달을 닫는 함수 전달
                memberId={null}                         // memberId를 null로 전달하여 모달 내부의 더미 데이터 로직을 트리거
            />

            {/* 다른 컴포넌트들은 현재 주석 처리되어 있습니다. */}
        </div>
    
    );
}

export default App
