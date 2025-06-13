import { useState } from "react";
import RadioButton from "../components/button/RadioButton";
import InfoBox from "../components/infoBox/InfoBox";
import CareerInfo from "../components/resume/CareerInfo";
import CertificationInfo from "../components/resume/CertificationInfo";
import EducationInfo from "../components/resume/EducationInfo";
import LanguageTest from "../components/resume/LanguageTest";
import ModalTitle from "../components/title/ModalTitle";
import type { resumeProps, coverLetterProps, careerDescriptionProps, aiConsultingDetailProps } from "../props/AiConsultingProps";
import CommonModal from "./CommonModal";
import CoverLetterInfo from "../components/coverLetter/CoverLetterInfo";
import CareerDescriptionInfo from "../components/careerDescription/CareerDescriptionInfo";
import AiConsulting from "../components/aiConsulting/AiConsulting";
import { documentTypeEnum, resumeTypeEnum } from "../enum/Enum";

interface Props {
    aiConsultingDetail: aiConsultingDetailProps
}


const AiConsultingEditDetailModal = ({
    aiConsultingDetail
}: Props) => {

    const resumeJson = JSON.parse(aiConsultingDetail.resume) as resumeProps;
    const educations = resumeJson.educations;
    const careers = resumeJson.careers;
    const certifications = resumeJson.certifications;
    const languageTests = resumeJson.languageTests;
    const coverLetterJson = JSON.parse(aiConsultingDetail.coverLetter) as coverLetterProps;
    const careerDescriptionJson = JSON.parse(aiConsultingDetail.careerDescription) as careerDescriptionProps;
    const [checkedDocument, setCheckedDocument] = useState(documentTypeEnum[0].value);
    const [checkedResume, setCheckedResume] = useState(resumeTypeEnum[0].value);



    return (

        <>
            <CommonModal size="l" onClose={() => console.log("닫기")}>
                <ModalTitle title="피드백 요청 상세" />
                <div className="grid grid-cols-3 gap-6">
                    <InfoBox label="지원 기업" value={aiConsultingDetail.targetCompanyName} />
                    <InfoBox label="지원 부문" value={aiConsultingDetail.targetJob} />
                    <InfoBox label="요청 일자" value={aiConsultingDetail.requestedDate} />
                </div>

                {/* 문서 종류 선택 탭 */}
                <div className="space-x-[122px]">
                    <div className="w-full border-t border-gray-300 my-2" />
                    <RadioButton
                        name=""
                        textList={documentTypeEnum}
                        checkedText={checkedDocument}
                        setCheckedText={setCheckedDocument}
                        size="document"
                    />
                </div>

                {/* ✅ 이력서 선택 시: 학력/경력/자격증/어학 */}
                {checkedDocument === "이력서" && (
                    <div>
                        <div className="w-full border-t border-gray-300 my-2" />
                        {/* 태그 선택 */}
                        <div className="flex gap-[24px] justify-center ml-[-80px] mt-4">

                            <RadioButton
                                name=""
                                textList={resumeTypeEnum}
                                checkedText={checkedResume}
                                setCheckedText={setCheckedResume}
                            />
                        </div>

                        <div className="flex justify-center">
                            {checkedResume === "학력사항" && (<EducationInfo educations={educations} />)}
                            {checkedResume === "경력사항" && (<CareerInfo careers={careers} />)}
                            {checkedResume === "자격증" && (<CertificationInfo certifications={certifications} />)}
                            {checkedResume === "어학" && (<LanguageTest languageTests={languageTests} />)}
                        </div>
                    </div>
                )}




                {checkedDocument === "경력기술서" && (
                    <>
                        <CareerDescriptionInfo careerDescription={careerDescriptionJson} />
                        <AiConsulting aiContents={aiConsultingDetail.aiContents} consultingType={"첨삭"} />
                    </>
                )}

                {checkedDocument === "자기소개서" && (
                    <>
                        <CoverLetterInfo coverLetter={coverLetterJson} />
                        <AiConsulting aiContents={aiConsultingDetail.aiContents} consultingType={"첨삭"} />
                    </>
                )}


            </CommonModal>
        </>
    )

};


export default AiConsultingEditDetailModal;

