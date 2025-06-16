import { useState, useEffect } from "react";
import type { consultingTypeEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import BasicInfoInputModal from "./BasicInfoInputModal";
import ResumeOutputForRequestModal from "./ResumeOutputForRequestModal";
import { useRequestController } from "../customHooks/useRequestController";
import CareerDescriptionSelectForRequestModal from "./CareerDescriptionSelectForRequestModal";
import CoverLetterSelectForRequestModal from "./CoverLetterSelectForRequestModal";

interface RequestModalProps {
    consultingType: consultingTypeEnum
}

const RequestModal = ({
    consultingType
}: RequestModalProps) => {

    const {
        showModalNumber,
        setShowRequestModal,
        setShowModalNumber
    } = useRequestController();

    const onClose = () =>{
        setShowModalNumber(-1)
        setShowRequestModal(false);
    }

    //모달 내 상태 선언부
    const [modalSize, setModalSize] = useState<"s" | "m" | "l">('m');

    // 모달 내 동작부
    useEffect(() => {
        if (showModalNumber === 0) {
            setModalSize('m');
        }
        else if (showModalNumber === 4) {
            onClose();
        }
        else {
            setModalSize('l');
        }
    }, [showModalNumber]);

    if (showModalNumber < 0 || showModalNumber >= 4) return null;

    return (
        <>


            <CommonModal size={modalSize} onClose={onClose} >
                {showModalNumber === 0 &&
                    (<BasicInfoInputModal />)}
                {showModalNumber === 1 &&
                    (<ResumeOutputForRequestModal  />)}
                {showModalNumber === 2 &&
                    (<CareerDescriptionSelectForRequestModal />)}
                {showModalNumber === 3 &&
                    (<CoverLetterSelectForRequestModal consultingType={consultingType}/>)}
            </CommonModal >
        </>
    )

}

export default RequestModal;