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
import AiFeedback from "../components/aiConsulting/AiFeedback";
import CareerDescriptionInfo from "../components/careerDescription/CareerDescriptionInfo";

interface Props {
    aiConsultingDetail: aiConsultingDetailProps
}

const requestTextList = [
    { label: "이력서", value: "resume" },
    { label: "자기소개서", value: "coverLetter" },
    { label: "경력기술서", value: "careerDescription" },
]

const resumeTextList = [
    { label: "학력 사항", value: "resume" },
    { label: "경력 사항", value: "career" },
    { label: "자격증", value: "certification" },
    { label: "어학", value: "languageTest" },
]


const AiConsultingFeedbackDetailModal = ({
    aiConsultingDetail
}: Props) => {

    const resumeJson = JSON.parse(aiConsultingDetail.resume) as resumeProps;
    const educations = resumeJson.educations;
    const careers = resumeJson.careers;
    const certifications = resumeJson.certifications;
    const languageTests = resumeJson.languageTests;

    const coverLetterJson = JSON.parse(aiConsultingDetail.coverLetter) as coverLetterProps;

    const careerDescriptionJson = JSON.parse(aiConsultingDetail.careerDescription) as careerDescriptionProps;

    const [checkedText, setCheckedText] = useState("resume");
    const [selectedTag, setSelectedTag] = useState("education");



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
                        textList={requestTextList}
                        checkedText={checkedText}
                        setCheckedText={setCheckedText}
                    />
                </div>

                {/* ✅ 이력서 선택 시: 학력/경력/자격증/어학 */}
                {checkedText === "resume" && (
                    <div>
                        <div className="w-full border-t border-gray-300 my-2" />
                        {/* 태그 선택 */}
                        <div className="flex gap-[24px] justify-center ml-[-80px] mt-4">

                            <RadioButton
                                name=""
                                textList={resumeTextList}
                                checkedText={selectedTag}
                                setCheckedText={setSelectedTag}
                            />
                        </div>

                        {/* 선택된 항목에 따라 입력 폼 표시 */}
                        <div className="flex justify-center">
                            {selectedTag === "education" && (<EducationInfo educations={educations} />)}

                            {selectedTag === "career" && (<CareerInfo careers={careers} />)}

                            {selectedTag === "certification" && (<CertificationInfo certifications={certifications} />)}

                            {selectedTag === "languageTest" && (<LanguageTest languageTests={languageTests} />)}
                        </div>
                    </div>
                )}

                {checkedText === "coverLetter" && (
                    <>
                        <CoverLetterInfo coverLetter={coverLetterJson} />
                        <AiFeedback aiContents={aiConsultingDetail.aiContents} />
                    </>
                )}


                {checkedText === "careerDescription" && (
                    <>
                        <CareerDescriptionInfo careerDescription={careerDescriptionJson} />
                        <AiFeedback aiContents={aiConsultingDetail.aiContents} />
                    </>
                )}


            </CommonModal>
        </>
    )

};


export default AiConsultingFeedbackDetailModal;



// const [schoolName, setSchoolName] = useState("");
// const [major, setMajor] = useState("");
// const [educationLevel, setEducationLevel] = useState("");
// const [gpa, setGpa] = useState("");
// const [location, setLocation] = useState("");
// const [enterDate, SetEnterDate] = useState("");
// const [graduate, SetGraduate] = useState("");
// const [companyName, setCompanyName] = useState("");
// const [job, setJob] = useState("");
// const [department, setDepartment] = useState("");
// const [position, setPosition] = useState("");
// const [startDate, setStartDate] = useState("");
// const [endDate, setEndDate] = useState("");
// const [certificationName, setCertificationName] = useState("");
// const [issuingOrg, setIssuingOrg] = useState("");
// const [grade, setGrade] = useState("");
// const [score, setScore] = useState("");
// const [certificationNo, setCertificationNo] = useState("");
// const [acquisitionDate, setAcquisitionDate] = useState("");


// const [languageType, setLanguageType] = useState("");
// const [testName, setTestName] = useState("");
// const [languageIssuingOrg, setLanguageIssuingOrg] = useState("");
// const [languageGrade, setLanguageGrade] = useState("");
// const [languageScore, setLanguageScore] = useState("");
// const [languageCertificationNo, setLanguageCertificationNo] = useState("");
// const [languageAcquisitionDate, setLanguageAcquisitionDate] = useState("");
// const [personality, setPersonality] = useState("");
// const [applicationMotivation, setApplicationMotivation] = useState("");
// const [coverLetterAiFeedback, setCoverLetterAiFeedback] = useState("");
// const [careerDescriptionAiFeedback_2, setCoverLetterAiFeedback_2] = useState("");
// const [careerDescriptionAiFeedback, setCareerDescriptionAiFeedback] = useState("");
// const [consultantCoverLetterFeedback, setConsultantCoverLetterFeedback] = useState("");
// const [consultantCareerDescriptionFeedback, setConsultantCareerDescriptionFeedback] = useState("");
// const [consultantCareerDescriptionFeedback_2, setConsultantCareerDescriptionFeedback_2] = useState("");
// const [coverLetter, setCoverLetter] = useState("");
// const [careerDescription, setCareerDescription] = useState("");
// const [careerDescription_2, setCareerDescription_2] = useState("");