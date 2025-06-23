import { useEffect } from "react";



import type { CareerDescriptionResponseDto, CareerDescriptionUpdateDto } from "./features/myPageForMember/props/myPageForMemberProps";
import JobPostingForMemberPage from "./features/jobPostingForMember/pages/JobPostingForMemberPage";
import { JobPostingForMemberPageProvider } from "./features/jobPostingForMember/contexts/JobPostingForMemberPageProvider";
import CareerDescriptionDetailModal from "./features/myPageForMember/modals/CareerDescriptionDetailModal";
import DocumentTab from "./features/myPageForMember/tabs/documentTab/DocumentTab";
import { DocumentTabProvider } from "./features/myPageForMember/contexts/DocumentTab/DocumentTabProvider";
import MyPageForMemberPage from "./features/myPageForMember/pages/MyPageForMemberPage";
import { MyPageForMemberPageProvider } from "./features/myPageForMember/contexts/MyPageForMemberPage/MyPageForMemberPagePageProvider";
import SignUpModal from "./features/auth/modals/SignUpModal";
import AuthModal from "./features/auth/modals/AuthModal";
import Header from "./features/header/Header";
import NavigationBar from "./features/navigationBar/NavigationBar";
import { RouterProvider } from "react-router-dom";
import router from "./features/routers/router";



// export const mockCareerDescriptionResponseDto: CareerDescriptionResponseDto = {
//     id: 1,
//     title: "백엔드 개발자 경력기술서",
//     contents: [
//         {
//             id: 1,
//             item: "주요 업무",
//             content: "Spring Boot 기반 REST API 설계 및 개발을 수행하였습니다.",
//         },
//         {
//             id: 2,
//             item: "성과",
//             content: "기존 시스템의 응답속도를 40% 향상시켰습니다.",
//         }
//     ],
//     createdDate: "2024-05-01"
// };

export const mockCareerDescriptionResponseDto: CareerDescriptionResponseDto = {
    id: 1,
    title: "백엔드 개발자 경력기술서",
    contents: [
        {
            id: 101,
            item: "프로젝트: 쇼핑몰 백엔드 시스템 개발",
            content: "Spring Boot와 JPA를 활용하여 상품 관리, 주문 처리, 회원 인증 기능 구현. JWT 인증 적용 및 소프트 딜리트 구조 설계."
        },
        {
            id: 102,
            item: "프로젝트: 채용 플랫폼 마이페이지 기능",
            content: "React, Redux를 활용한 상태 관리 및 커스텀 훅 설계. 회원 이력서 및 지원 내역 조회 기능 구현."
        }
    ],
    createdDate: "2025-06-19T12:00:00"
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
            <RouterProvider router={router} />
        </>
    )

}

export default App
