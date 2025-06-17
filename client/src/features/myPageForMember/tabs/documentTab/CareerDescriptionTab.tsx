
import { useState } from "react";
import { BigIntenalBox, ExternalBox, MiddleIntenalBox } from "../../../../common/components/box/Box";
import Button from "../../../../common/components/button/Button";
import Input from "../../../../common/components/input/Input";
import TextArea from "../../../../common/components/input/TextArea";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import MyPageSelect from "../../components/MyPageSelect";
import { useDocumentTabApi } from "../../customHooks/DocumentTab/useDocumentTabApi";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";
import type { CareerDescriptionSummaryDto } from "../../props/myPageForMemberProps";

const mockCareerDescriptionSummaryDtos: CareerDescriptionSummaryDto[] = [
    {
        id: 1,
        title: "백엔드 개발자 - 쇼핑몰 프로젝트 경험",
        createdDate: "2024-11-10",
    },
    {
        id: 2,
        title: "AI 기반 추천 시스템 설계 경험",
        createdDate: "2025-01-05",
    },
    {
        id: 3,
        title: "Spring Boot + JPA 기반 CRUD API 개발",
        createdDate: "2024-12-22",
    },
    {
        id: 4,
        title: "JWT 인증 기반 보안 설계",
        createdDate: "2025-02-15",
    },
    {
        id: 5,
        title: "팀 프로젝트 리더 경험 - 기능 기획 및 문서화",
        createdDate: "2025-03-03",
    },
];


const CareerDescriptionTab = () => {

    const {
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId,
        careerDescriptionSummaryDtos,
        careerDescriptionResponseDto,
        showDetailModal,
        setShowDetailModal,
    } = useDocumentTabController();

    const {
        readCareerDescriptionResponseDto,
    } = useDocumentTabApi();

    const onClickList = (id: number) => {
        setClickedCareerDescriptionId(id);
        readCareerDescriptionResponseDto(id);
    };




    return (
        <div>
            {mockCareerDescriptionSummaryDtos.map(careerDescriptionSummaryDto => (
                <>
                    <MyPageSelect
                        careerOrCoverLetterSummaryDto={careerDescriptionSummaryDto}
                        isClicked={clickedCareerDescriptionId === careerDescriptionSummaryDto.id}
                        onClickList={() => onClickList(careerDescriptionSummaryDto.id)} />
                </>
            ))}
            <div className="flex justify-end mr-6">
                <Button color={"theme"} size={"m"} disabled={false} text={"경력기술서 추가"} type={"button"} />
            </div>
        </div>
    )
}

export default CareerDescriptionTab
