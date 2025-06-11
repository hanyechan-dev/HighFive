import Input from "../../common/components/input/Input";
import ModalTitle from "../../common/components/title/ModalTitle";
import CommonModal from "../../common/modals/CommonModal"
import Button from "../../common/components/button/Button";

interface BasicInfoInputModalPrpos {
    targetJob: string;
    setTargetJob: (targetJob: string) => void;
    targetCompanyName: string;
    setTargetCompanyName: (targetCompanyName: string) => void;
    setShowBasicInfoInputModal: (showBasicInfoInputModal: boolean) => void;
    setModalNumber: (modalNumber: number) => void
}

const BasicInfoInputModal = ({
    targetJob,
    setTargetJob,
    targetCompanyName,
    setTargetCompanyName,
    setShowBasicInfoInputModal,
    setModalNumber
}
    : BasicInfoInputModalPrpos) => {

    const onClose = () => { setShowBasicInfoInputModal(false) };

    const onClickNext = () => {
        onClose();
        setModalNumber(1);
    }

    return (
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