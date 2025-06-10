import { useState } from "react";
import Input from "../../common/components/input/Input";
import ModalTitle from "../../common/components/title/ModalTitle";
import CommonModal from "../../common/modals/CommonModal"
import Button from "../../common/components/button/Button";


const BasicInfoInputModal = () =>{

    const[targetJob, setTargetJob] = useState("");
    const[targetCompanyName, setTargetCompanyName] = useState("");
    const[showModal, setShowModal] = useState(true);
    const onClose = () => { setShowModal(false) };
    const onClickNext = () =>{
        onClose();
    }

    if (!showModal) return null

    return(
        <CommonModal size={"m"} onClose={onClose} >
            <ModalTitle title={"기본 정보 입력"} />
            <Input label={"희망 기업"} placeholder={""} size={"m"} disabled={false} type={"text"} value={targetCompanyName} setValue={setTargetCompanyName} />
            <Input label={"희망 직무"} placeholder={""} size={"m"} disabled={false} type={"text"} value={targetJob} setValue={setTargetJob} />
            <div className="flex justify-end mr-6">
            <Button color={"theme"} size={"m"} disabled={false} text={"저장 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </CommonModal>
    )
};

export default BasicInfoInputModal;