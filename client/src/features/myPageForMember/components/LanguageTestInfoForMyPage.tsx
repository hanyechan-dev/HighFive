import { useState } from "react";
import { BigIntenalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import type { LanguageTestResponseDto } from "../props/myPageForMemberProps";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";

interface LanguageTestInfoForMyPageProps {
    languageTestResponseDto: LanguageTestResponseDto;
}


const LanguageTestInfoForMyPage = ({ languageTestResponseDto }: LanguageTestInfoForMyPageProps) => {


    const [languageTest, setLanguageTest] = useState<LanguageTestResponseDto>(languageTestResponseDto);

    const {
        id,
        languageType,
        testName,
        issuingOrg,
        grade,
        score,
        certificationNo,
        acquisitionDate,
    } = languageTest;

    const setLanguageTestField = (field: keyof LanguageTestResponseDto) => (value: string) => {
        setLanguageTest(prev => ({
            ...prev,
            [field]: value
        }));
    };

    const {
        updateLanguageTest,
        deleteLanguageTest
    } = useDocumentTabApi();


    const [isChangeButtonClicked, setIsChangeButtonClicked] = useState(false);
    const onClickChangeButton = () => {
        setIsChangeButtonClicked(true);
    }
    const onClickSaveButton = () => {

        updateLanguageTest(languageTest);
        setIsChangeButtonClicked(false);
    }

    const onClickDeleteButton = () => {
        deleteLanguageTest(id)
    }

    return (
        <BigIntenalBox>
            <div className="flex">
                <Input label={"언어"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={languageType} setValue={setLanguageTestField("languageType")} />
                <Input label={"시험명"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={testName} setValue={setLanguageTestField("testName")} />
            </div>
            <div className="flex">
                <Input label={"발급기관"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={issuingOrg} setValue={setLanguageTestField("issuingOrg")} />
                <Input label={"등급"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={grade} setValue={setLanguageTestField("grade")} />
            </div>
            <div className="flex">
                <Input label={"점수"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={score} setValue={setLanguageTestField("score")} />
                <Input label={"자격증번호"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={certificationNo} setValue={setLanguageTestField("certificationNo")} />
            </div>
            <Input label={"취득일"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"date"} value={acquisitionDate} setValue={setLanguageTestField("acquisitionDate")} />

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

export default LanguageTestInfoForMyPage;