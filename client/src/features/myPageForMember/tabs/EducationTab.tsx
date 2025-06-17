import { useState } from "react";
import Button from "../../../common/components/button/Button";
import EducationCreate from "../components/EducationCreate";
import EducationInfoForMyPage from "../components/EducationInfoForMyPage";
import { useMyPageForMemberPageController } from "../customHooks/useMyPageForMemberPageController";
import EmptyState from "../../../common/components/emptyState/EmptyState";

const EducationTab = () => {

    
    const [isAddEducationMode, setIsEducationMode] = useState(false);

    const {
        resume,
        setEducationCreateDto,
        setEducationUpdateDto,
    } = useMyPageForMemberPageController();

    const { educationResponseDtos } = resume

    
    const onclickAddEducation = () => {
        setIsEducationMode(true);
    }

    return (
        <div>
            {educationResponseDtos.length > 0 ? educationResponseDtos.map(educationResponseDto => (
                <EducationInfoForMyPage
                    key={educationResponseDto.id}
                    educationResponseDto={educationResponseDto}
                    setEducationUpdateDto={setEducationUpdateDto}
                />
            )) : (!isAddEducationMode && <EmptyState title={"등록된 학력 사항이 없습니다."} text={"학력 사항을 등록해주세요."} />)}

            {isAddEducationMode && <EducationCreate setEducationCreateDto={setEducationCreateDto} setIsEducationMode={setIsEducationMode} />}

            <div className="flex justify-end mr-6">
                <Button
                    text="추가"
                    size="s"
                    color="theme"
                    onClick={onclickAddEducation}
                    type="button" disabled={isAddEducationMode} />
            </div>
        </div>

    )

}

export default EducationTab;