
import { useState } from "react";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { useRequestController } from "../customHooks/useRequestController";


const BasicInfoInputModal = () => {

    const {
        targetJob,
        setTargetJob,
        targetCompanyName,
        setTargetCompanyName,
        setShowModalNumber
    } = useRequestController();

    const [showAlert, setShowAlert] = useState(false);

    const onClickNext = () => {
        if (targetCompanyName.trim() === '' || targetJob.trim() === '') {
            setShowAlert(true);
        } else {
            setShowModalNumber(1);
        }
    }

    return (
        <>
            <ModalTitle title={"기본 정보 입력"} />
            <Input label={"희망 기업"} placeholder={""} size={"m"} disabled={false} type={"text"} value={targetCompanyName} setValue={setTargetCompanyName} />
            <Input label={"희망 직무"} placeholder={""} size={"m"} disabled={false} type={"text"} value={targetJob} setValue={setTargetJob} />
            {showAlert && (<div className="flex justify-end pr-6 pb-6 text-sm font-roboto text-blue-400">희망 기업 / 직무를 입력해주세요</div>)}
            <div className="flex justify-end mr-6">
                <Button color={"theme"} size={"m"} disabled={false} text={"저장 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </>
    )
};

export default BasicInfoInputModal;