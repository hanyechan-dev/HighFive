import { useState } from "react";
import { BigIntenalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import type { EducationCreateDto } from "../props/myPageForMemberProps";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";
import { validateEducation } from "../../../common/utils/ValidationUtil";

interface EducationCreateProps {
    setIsAddEducationMode: (isAddEducationMode: boolean) => void
}


const EducationCreate = ({ setIsAddEducationMode }: EducationCreateProps) => {




    const [education, setEducation] = useState<EducationCreateDto>({
        schoolName: "",
        educationLevel: "",
        major: "",
        gpa: "",
        location: "",
        enterDate: "",
        graduateDate: null,
    });

    const {
        schoolName,
        educationLevel,
        major,
        gpa,
        location,
        enterDate,
        graduateDate,
    } = education;

    const setEducationField = (field: keyof EducationCreateDto) => (value: string) => {
        setEducation(prev => ({
            ...prev,
            [field]: value
        }));
    };

    const {
        createEducation
    } = useDocumentTabApi();


    const [isChangeButtonClicked, setIsChangeButtonClicked] = useState(true);
    const onClickChangeButton = () => {
        setIsChangeButtonClicked(true);
    }
    const onClickSaveButton = () => {

        const validationMessage = validateEducation(education);
        if (validationMessage) {
            alert(validationMessage);
            return;
        }

        createEducation(education);
        setIsChangeButtonClicked(false);
        setIsAddEducationMode(false);
    }

    return (
        <BigIntenalBox>
            <div className="flex">
                <Input label={"학교명"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={schoolName} setValue={setEducationField("schoolName")} />
                <Input label={"전공"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={major} setValue={setEducationField("major")} />
            </div>
            <div className="flex">
                <Input label={"학위"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={educationLevel} setValue={setEducationField("educationLevel")} />
                <Input label={"학점"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={gpa} setValue={setEducationField("gpa")} />
            </div>
            <div className="flex">
                <Input label={"소재지"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={location} setValue={setEducationField("location")} />
                <Input label={"입학일"} placeholder={""} size={"bibs"} disabled={!isChangeButtonClicked} type={"date"} value={enterDate} setValue={setEducationField("enterDate")} />
                <Input label={"졸업일"} placeholder={""} size={"bibs"} disabled={!isChangeButtonClicked} type={"date"} value={graduateDate || ""} setValue={setEducationField("graduateDate")} />
            </div>

            <div className="flex justify-end mr-6">
                {isChangeButtonClicked ?
                    <Button color={"theme"} size={"s"} disabled={false} text={"저장"} type={"button"} onClick={onClickSaveButton} />
                    :
                    <Button color={"theme"} size={"s"} disabled={false} text={"수정"} type={"button"} onClick={onClickChangeButton} />
                }
            </div>

        </BigIntenalBox>
    )
}

export default EducationCreate;