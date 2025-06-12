import { useEffect, useState } from "react";
import BasicInfoInputModal from "./BasicInfoInputModal";
import CareerDescriptionSelectModal from "./CareerDescriptionSelectModal";
import CoverLetterSelectModal from "./CoverLetterSelectModal";
import type { careerDescriptionOrCoverLetterSummaryProps, resumeProps } from "../../common/props/AiConsultingProps";

import CommonModal from "../../common/modals/CommonModal";
import { createRequestApi, readCareerDescriptionsApi, readCareersApi, readCertificationsApi, readCoverLettersApi, readEducationsApi, readLanguageTestsApi } from "../member/MemberApi";
import type { consultingTypeEnum } from "../../common/enum/Enum";
import ResumeOutputModal from "./ResumeOutputModal";


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
    const [resume, setResume] = useState<resumeProps>()
    const [careerDescriptions, setCareerDescriptions] = useState<careerDescriptionOrCoverLetterSummaryProps[]>([])
    const [coverLetters, setCoverLetters] = useState<careerDescriptionOrCoverLetterSummaryProps[]>([])


    useEffect(() => {

        if (showModalNumber === 0) {
            setModalSize('m');

        } else if (showModalNumber === 1) {
            setModalSize('l');
            const fetchData = async () => {

                try {

                    const educations = (await readEducationsApi()).data.content;
                    const careers = (await readCareersApi()).data.content;
                    const certifications = (await readCertificationsApi()).data.content;
                    const languageTests = (await readLanguageTestsApi()).data.content;
                    setResume({
                        educations,
                        careers,
                        certifications,
                        languageTests
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
                        resume={resume ?? { educations: [], careers: [], certifications: [], languageTests: [] }} />)}


                {showModalNumber === 2 &&
                    (<CareerDescriptionSelectModal
                        targetJob={targetJob}
                        targetCompanyName={targetCompanyName}
                        setShowModalNumber={setShowModalNumber}
                        careerDescriptions={careerDescriptions ?? []}
                        clickedCareerDescriptionId={clickedCareerDescriptionId}
                        setClickedCareerDescriptionId={setClickedCareerDescriptionId} />)}


                {showModalNumber === 3 &&
                    (<CoverLetterSelectModal
                        targetJob={targetJob}
                        targetCompanyName={targetCompanyName}
                        setShowModalNumber={setShowModalNumber}
                        coverLetters={coverLetters ?? []}
                        clickedCoverLetterId={clickedCoverLetterId}
                        setClickedCoverLetterId={setClickedCoverLetterId}
                        consultingType={consultingType} />)}
            </CommonModal >
        </>
    )

}

export default RequestModal;