import { useState } from "react";
import { BigIntenalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import type { CertificationResponseDto } from "../props/myPageForMemberProps";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";

interface CertificationInfoForMyPageProps {
    certificationResponseDto: CertificationResponseDto;
}


const CertificationInfoForMyPage = ({ certificationResponseDto }: CertificationInfoForMyPageProps) => {


    const [certification, setCertification] = useState<CertificationResponseDto>(certificationResponseDto);

    const {
        id,
        certificationName,
        issuingOrg,
        grade,
        score,
        certificationNo,
        acquisitionDate,
    } = certification;

    const setCertificationField = (field: keyof CertificationResponseDto) => (value: string) => {
        setCertification(prev => ({
            ...prev,
            [field]: value
        }));
    };

    const {
        updateCertification,
        deleteCertification
    } = useDocumentTabApi();


    const [isChangeButtonClicked, setIsChangeButtonClicked] = useState(false);
    const onClickChangeButton = () => {
        setIsChangeButtonClicked(true);
    }
    const onClickSaveButton = () => {

        updateCertification(certification);
        setIsChangeButtonClicked(false);
    }

    const onClickDeleteButton = () => {
        deleteCertification(id)
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

export default CertificationInfoForMyPage;