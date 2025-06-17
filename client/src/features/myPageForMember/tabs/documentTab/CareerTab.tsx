
import { useState } from "react";
import EmptyState from "../../../../common/components/emptyState/EmptyState";
import CareerCreate from "../../components/CareerCreate";
import CareerInfoForMyPage from "../../components/CareerInfoForMyPage";
import Button from "../../../../common/components/button/Button";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";


const CareerTab = () => {

    
    const [isAddCareerMode, setIsAddCareerMode] = useState(false);

    const {
        resume,
    } = useDocumentTabController();

    const { careerResponseDtos } = resume

    
    const onclickAddCareer = () => {
        setIsAddCareerMode(true);
    }

    return (
        <div>
            {careerResponseDtos.length > 0 ? careerResponseDtos.map(careerResponseDto => (
                <CareerInfoForMyPage
                    key={careerResponseDto.id}
                    careerResponseDto={careerResponseDto}
                />
            )) : (!isAddCareerMode && <EmptyState title={"등록된 경력 사항이 없습니다."} text={"경력 사항을 등록해주세요."} />)}

            {isAddCareerMode && <CareerCreate setIsAddCareerMode={setIsAddCareerMode} />}

            <div className="flex justify-end mr-6">
                <Button
                    text="추가"
                    size="s"
                    color="theme"
                    onClick={onclickAddCareer}
                    type="button" disabled={isAddCareerMode} />
            </div>
        </div>

    )

}

export default CareerTab;