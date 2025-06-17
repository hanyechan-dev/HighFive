
import { useState } from "react";
import EmptyState from "../../../../common/components/emptyState/EmptyState";
import EducationCreate from "../../components/EducationCreate";
import EducationInfoForMyPage from "../../components/EducationInfoForMyPage";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";
import Button from "../../../../common/components/button/Button";


const EducationTab = () => {





    const [isAddEducationMode, setIsAddEducationMode] = useState(false);

    const {
        resume
    } = useDocumentTabController();

    const { educationResponseDtos } = resume

    const onclickAddEducation = () => {
        setIsAddEducationMode(true);
    }

    return (
        <div>
            {educationResponseDtos.length > 0 ? educationResponseDtos.map(educationResponseDto => (
                <EducationInfoForMyPage
                    key={educationResponseDto.id}
                    educationResponseDto={educationResponseDto}
                />
            )) : (!isAddEducationMode && <EmptyState title={"등록된 학력 사항이 없습니다."} text={"학력 사항을 등록해주세요."} />)}

            {isAddEducationMode && <EducationCreate setIsAddEducationMode={setIsAddEducationMode} />}

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