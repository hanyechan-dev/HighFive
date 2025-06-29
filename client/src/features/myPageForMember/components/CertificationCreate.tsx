import { useState } from "react";
import { BigIntenalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import type { CertificationCreateDto } from "../props/myPageForMemberProps";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";

interface CertificationCreateProps {
    setIsAddCertificationMode: (isAddCertificationMode: boolean) => void
}


const CertificationCreate = ({setIsAddCertificationMode }: CertificationCreateProps) => {


    const [certification, setCertification] = useState<CertificationCreateDto>({
        certificationName: "",
        issuingOrg: "",
        grade: "",
        score: "",
        certificationNo: "",
        acquisitionDate: "",
    });

    const {
        certificationName,
        issuingOrg,
        grade,
        score,
        certificationNo,
        acquisitionDate,
    } = certification;

    const setCertificationField = (field: keyof CertificationCreateDto) => (value: string) => {
        setCertification(prev => ({
            ...prev,
            [field]: value
        }));
    };

    const {
        createCertification
    } = useDocumentTabApi();


    const [isChangeButtonClicked, setIsChangeButtonClicked] = useState(true);
    const onClickChangeButton = () => {
        setIsChangeButtonClicked(true);
    }
    const onClickSaveButton = () => {

        createCertification(certification);
        setIsChangeButtonClicked(false);
        setIsAddCertificationMode(false);
    }

    return (
        <BigIntenalBox>
            <div className="flex">
                <Input label={"자격증"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={certificationName} setValue={setCertificationField("certificationName")} />
                <Input label={"발급 기관"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={issuingOrg} setValue={setCertificationField("issuingOrg")} />
            </div>
            <div className="flex">
                <Input label={"등급"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={grade} setValue={setCertificationField("grade")} />
                <Input label={"점수"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={String(score)} setValue={setCertificationField("score")} />
            </div>
            <div className="flex">
                <Input label={"자격증번호"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"text"} value={certificationNo} setValue={setCertificationField("certificationNo")} />
                <Input label={"취득일"} placeholder={""} size={"bibm"} disabled={!isChangeButtonClicked} type={"date"} value={acquisitionDate} setValue={setCertificationField("acquisitionDate")} />
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

export default CertificationCreate;