import { useState, useEffect } from "react";
import type { consultingTypeEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import type { Resume, CareerDescriptionSummaryDto, CoverLetterSummaryDto } from "../props/RequestProps";
import BasicInfoInputModal from "./BasicInfoInputModal";
import CareerDescriptionSelectModal from "./CareerDescriptionSelectModal";
import CoverLetterSelectModal from "./CoverLetterSelectModal";
import ResumeOutputModal from "./ResumeOutputModal";


export interface RequestModalInfoProps {
    targetJob: string;
    targetCompanyName: string;
    resume: Resume;
    careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[];
    coverLetterSummaryDtos: CoverLetterSummaryDto[];
    clickedCareerDescriptionId: number;
    clickedCoverLetterId: number;
    showModalNumber: number;
    setTargetJob: (targetJob: string) => void;
    setTargetCompanyName: (targetCompanyName: string) => void;
    setClickedCareerDescriptionId: (clickedCareerDescriptionId: number) => void;
    setClickedCoverLetterId: (clickedCoverLetterId: number) => void;
    setShowModalNumber: (showModalNumber: number) => void;
}


interface RequestModalProps {
    requestModalInfo: RequestModalInfoProps;
    onClose: () => void
    consultingType: consultingTypeEnum
}


const RequestModal = ({
    requestModalInfo: {
        targetJob,
        targetCompanyName,
        resume,
        careerDescriptionSummaryDtos,
        coverLetterSummaryDtos,
        clickedCareerDescriptionId,
        clickedCoverLetterId,
        showModalNumber,
        setTargetJob,
        setTargetCompanyName,
        setClickedCareerDescriptionId,
        setClickedCoverLetterId,
        setShowModalNumber
    },
    onClose,
    consultingType
}: RequestModalProps) => {


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

    if (showModalNumber < 0 && showModalNumber >= 4) return null;

    
    //모달 내 하위 모달 정보 조합부
    const basicInfoInputProps = {
        targetJob,
        setTargetJob,
        targetCompanyName,
        setTargetCompanyName,
        setShowModalNumber
    }

    const resumeOutputModalProps = {
        targetJob,
        targetCompanyName,
        setShowModalNumber,
        resume
    }

    const careerDescriptionSelectProps = {
        targetJob,
        targetCompanyName,
        setShowModalNumber,
        careerDescriptionSummaryDtos,
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId
    }

    const coverLetterSelectProps = {
        targetJob,
        targetCompanyName,
        setShowModalNumber,
        coverLetterSummaryDtos,
        clickedCoverLetterId,
        setClickedCoverLetterId,
        consultingType
    }



    return (
        <>


            <CommonModal size={modalSize} onClose={onClose} >
                {showModalNumber === 0 &&
                    (<BasicInfoInputModal basicInfoInputProps={basicInfoInputProps} />)}
                {showModalNumber === 1 &&
                    (<ResumeOutputModal resumeOutputModalProps={resumeOutputModalProps} />)}
                {showModalNumber === 2 &&
                    (<CareerDescriptionSelectModal careerDescriptionSelectProps={careerDescriptionSelectProps} />)}
                {showModalNumber === 3 &&
                    (<CoverLetterSelectModal coverLetterSelectProps={coverLetterSelectProps}/>)}
            </CommonModal >
        </>
    )

}

export default RequestModal;