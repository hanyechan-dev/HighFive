import { useEffect, useState } from "react";
import BasicInfoInputModal from "./BasicInfoInputModal";
import CareerDescriptionSelectModal from "./CareerDescriptionSelectModal";
import CoverLetterSelectModal from "./CoverLetterSelectModal";

import CommonModal from "../../common/modals/CommonModal";
import { createRequestApi, readCareerDescriptionsApi, readCareersApi, readCertificationsApi, readCoverLettersApi, readEducationsApi, readLanguageTestsApi } from "../member/MemberApi";
import type { consultingTypeEnum } from "../../common/enum/Enum";
import ResumeOutputModal from "./ResumeOutputModal";
import type { CareerDescriptionSummaryDto, CareerResponseDto, CertificationResponseDto, CoverLetterSummaryDto, EducationResponseDto, LanguageTestResponseDto, ResumeJson } from "./RequestProps";


interface RequestModalProps {
    onClose: () => void
    consultingType: consultingTypeEnum
}


const RequestModal = ({
    onClose,
    consultingType
}: RequestModalProps) => {

    const [targetJob, setTargetJob] = useState('');
    const [targetCompanyName, setTargetCompanyName] = useState('');
    const [showModalNumber, setShowModalNumber] = useState(0);
    const [clickedCareerDescriptionId, setClickedCareerDescriptionId] = useState(-1);
    const [clickedCoverLetterId, setClickedCoverLetterId] = useState(-1);
    const [modalSize, setModalSize] = useState<"s" | "m" | "l">('m')
    const [resume, setResume] = useState<ResumeJson>()
    const [careerDescriptions, setCareerDescriptions] = useState<CareerDescriptionSummaryDto[]>([])
    const [coverLetters, setCoverLetters] = useState<CoverLetterSummaryDto[]>([])


    useEffect(() => {

        if (showModalNumber === 0) {
            setModalSize('m');

        } else if (showModalNumber === 1) {
            setModalSize('l');
            const fetchData = async () => {

                try {

                    const educationResponseDtos = (await readEducationsApi()).data.content as EducationResponseDto[];
                    const careerResponseDtos = (await readCareersApi()).data.content as CareerResponseDto[];
                    const certificationResponseDtos = (await readCertificationsApi()).data.content as CertificationResponseDto[];
                    const languageTestResponseDtos = (await readLanguageTestsApi()).data.content as LanguageTestResponseDto[];
                    setResume({
                        educationResponseDtos,
                        careerResponseDtos,
                        certificationResponseDtos,
                        languageTestResponseDtos
                    });

                } catch (err) {
                    console.error(err)

                }

            }

            fetchData();


        } else if (showModalNumber === 2) {
            setModalSize('l');
            const fetchData = async () => {

                try {

                    const careerDescriptions = (await readCareerDescriptionsApi()).data.content;
                    setCareerDescriptions(careerDescriptions);


                } catch (err) {
                    console.error(err)
                }
            }
            fetchData();
        } else if (showModalNumber === 3) {
            setModalSize('l');
            const fetchData = async () => {

                try {

                    const coverLetters = (await readCoverLettersApi()).data.content;
                    setCoverLetters(coverLetters);


                } catch (err) {
                    console.error(err)
                }
            }
            fetchData();

        } else if (showModalNumber === 4) {
            createRequestApi(targetJob, targetCompanyName, consultingType, clickedCoverLetterId, clickedCareerDescriptionId);
            onClose();

        }
    }, [showModalNumber]);

    if (showModalNumber === 4) return null;

    return (
        <>


            <CommonModal size={modalSize} onClose={onClose} >
                {showModalNumber === 0 &&
                    (<BasicInfoInputModal
                        targetJob={targetJob}
                        setTargetJob={setTargetJob}
                        targetCompanyName={targetCompanyName}
                        setTargetCompanyName={setTargetCompanyName}
                        setShowModalNumber={setShowModalNumber} />)}




                {showModalNumber === 1 &&
                    (<ResumeOutputModal
                        targetJob={targetJob}
                        targetCompanyName={targetCompanyName}
                        setShowModalNumber={setShowModalNumber}
                        resume={resume ?? { educationResponseDtos: [], careerResponseDtos: [], certificationResponseDtos: [], languageTestResponseDtos: [] }} />)}


                {showModalNumber === 2 &&
                    (<CareerDescriptionSelectModal
                        targetJob={targetJob}
                        targetCompanyName={targetCompanyName}
                        setShowModalNumber={setShowModalNumber}
                        careerDescriptionSummaryDtos={careerDescriptions ?? []}
                        clickedCareerDescriptionId={clickedCareerDescriptionId}
                        setClickedCareerDescriptionId={setClickedCareerDescriptionId} />)}


                {showModalNumber === 3 &&
                    (<CoverLetterSelectModal
                        targetJob={targetJob}
                        targetCompanyName={targetCompanyName}
                        setShowModalNumber={setShowModalNumber}
                        coverLetterSummaryDtos={coverLetters ?? []}
                        clickedCoverLetterId={clickedCoverLetterId}
                        setClickedCoverLetterId={setClickedCoverLetterId}
                        consultingType={consultingType} />)}
            </CommonModal >
        </>
    )

}

export default RequestModal;