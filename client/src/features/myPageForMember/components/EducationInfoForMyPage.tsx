import { useState } from "react";
import { BigIntenalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import type { EducationResponseDto } from "../props/myPageForMemberProps";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";
import Select from "../../../common/components/input/Select";
import { educationLevelEnum, regionEnum } from "../../../common/enum/Enum";

interface EducationInfoForMyPageProps {
    educationResponseDto: EducationResponseDto;
}


const EducationInfoForMyPage = ({ educationResponseDto }: EducationInfoForMyPageProps) => {


    const [education, setEducation] = useState<EducationResponseDto>(educationResponseDto);

    const {
        id,
        schoolName,
        educationLevel,
        major,
        gpa,
        location,
        enterDate,
        graduateDate,
    } = education;

    const setEducationField = (field: keyof EducationResponseDto) => (value: string) => {
        setEducation(prev => ({
            ...prev,
            [field]: value
        }));
    };


    const {
        updateEducation,
        deleteEducation
    } = useDocumentTabApi();


    const [isChangeButtonClicked, setIsChangeButtonClicked] = useState(false);
    const onClickChangeButton = () => {
        setIsChangeButtonClicked(true);
    }
    const onClickSaveButton = () => {


        updateEducation(education);
        setIsChangeButtonClicked(false);
    }

    const onClickDeleteButton = () =>{
        deleteEducation(id)
    }

    return (
        <BigIntenalBox>
            <div className="flex">
                <Input label={"학교명"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={schoolName} setValue={setEducationField("schoolName")} />
                <Input label={"전공"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={major} setValue={setEducationField("major")} />
            </div>
            <div className="flex">
                <Select label={"학위"} options={educationLevelEnum} size={"bibm"} disabled={!isChangeButtonClicked} value={educationLevel} setValue={setEducationField("educationLevel")} />
                <Input label={"학점"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={gpa} setValue={setEducationField("gpa")} />
            </div>
            <div className="flex">
                <Select label={"소재지"} options={regionEnum} size={"bibm"} disabled={!isChangeButtonClicked} value={location} setValue={setEducationField("location")} />
                <Input label={"입학일"} placeholder={""} size={"bibs"} disabled={!isChangeButtonClicked} type={"date"} value={enterDate} setValue={setEducationField("enterDate")} />
                <Input label={"졸업일"} placeholder={""} size={"bibs"} disabled={!isChangeButtonClicked} type={"date"} value={graduateDate || ""} setValue={setEducationField("graduateDate")} />
            </div>

            <div className="flex justify-end mr-6">
                <Button color={"action"} size={"s"} disabled={false} text={"삭제"} type={"button"} onClick={onClickDeleteButton} />
                {isChangeButtonClicked ?
                    <Button color={"theme"} size={"s"} disabled={false} text={"저장"} type={"button"} onClick={onClickSaveButton} />
                    :
                    <Button color={"theme"} size={"s"} disabled={false} text={"수정"} type={"button"} onClick={onClickChangeButton} />
                }
            </div>

        </BigIntenalBox>
    )
}

export default EducationInfoForMyPage;