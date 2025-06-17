import { useEffect } from "react";


import DocumentTab from "./features/myPageForMember/tabs/documentTab/DocumentTab";
import { DocumentTabProvider } from "./features/myPageForMember/contexts/DocumentTab/DocumentTabProvider";
import CDAndCLDetailModal from "./features/myPageForMember/modals/CDAndCLDetailModal";
import type { CareerDescriptionResponseDto } from "./features/myPageForMember/props/myPageForMemberProps";



export const mockCareerDescriptionResponseDto: CareerDescriptionResponseDto = {
    id: 1,
    title: "백엔드 개발자 경력기술서",
    contents: [
      {
        id: 1,
        item: "주요 업무",
        content: "Spring Boot 기반 REST API 설계 및 개발을 수행하였습니다.",
      },
      {
        id: 2,
        item: "성과",
        content: "기존 시스템의 응답속도를 40% 향상시켰습니다.",
      }
    ],
    createdDate: "2024-05-01"
  };


function App() {


    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지



    return (
        <>
            <DocumentTabProvider>
                <DocumentTab />
            </DocumentTabProvider>
            <CDAndCLDetailModal responseDto={mockCareerDescriptionResponseDto} documentType={"경력기술서"} onClose={function (): void {
                throw new Error("Function not implemented.");
            } } />
        </>
    )

}

export default App
