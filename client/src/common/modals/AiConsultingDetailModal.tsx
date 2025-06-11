import { useState } from "react"
import Box from "../components/box/Box"
import Button from "../components/button/Button"
import RadioButton from "../components/button/RadioButton"
import InfoBox from "../components/infoBox/InfoBox"
import Input from "../components/input/Input"
import TextArea from "../components/input/TextArea"
import ModalTitle from "../components/title/ModalTitle"
import CommonModal from "./CommonModal"
import Education from "../components/resume/Education"
import EducationInfo from "../components/resume/EducationInfo"
import CareerInfo from "../components/resume/CareerInfo"
import CertificationInfo from "../components/resume/CertificationInfo"
import LanguageTest from "../components/resume/LanguageTest"
import type { resumeProps } from "../props/resumeProps"

interface AiContentProps {
    item: string
    content: string
}

interface AiConsultingDetailModalProps {
    targetCompanyName: string;
    targetJob: string;
    requestedDate: string;
    resume: string;
    careerDescription: string;
    coverLetter: string;
    aiContents: AiContentProps[];
}






const AiConsultingDetailModal = ({

    targetCompanyName,
    targetJob,
    requestedDate,
    resume,
    careerDescription,
    coverLetter,
    aiContents

}: AiConsultingDetailModalProps) => {

    const resumeJson = JSON.parse(resume) as resumeProps;
    const educations = resumeJson.education;
    const careers = resumeJson.career;
    const certifications = resumeJson.certification;
    const languageTests = resumeJson.languageTest;



    const [checkedText, setCheckedText] = useState("resume");
    const [selectedTag, setSelectedTag] = useState("education");


    return (

        <>
            <CommonModal size="l" onClose={() => console.log("닫기")}>
                <ModalTitle title="피드백 요청 상세" />
                <div className="grid grid-cols-3 gap-6">
                    <InfoBox label="지원 기업" value={targetCompanyName} />
                    <InfoBox label="지원 부문" value={targetJob} />
                    <InfoBox label="요청 일자" value={requestedDate} />
                </div>

                {/* 문서 종류 선택 탭 */}
                <div className="space-x-[122px]">
                    <div className="w-full border-t border-gray-300 my-2" />
                    <RadioButton
                        name=""
                        textList={[
                            { label: "이력서", value: "resume" },
                            { label: "자기소개서", value: "coverLetter" },
                            { label: "경력기술서", value: "careerDescription" },
                        ]}
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
                                textList={[
                                    { label: "학력사항", value: "resume" },
                                    { label: "경력사항", value: "career" },
                                    { label: "자격증", value: "certification" },
                                    { label: "어학", value: "languageTest" },
                                ]}
                                checkedText={selectedTag}
                                setCheckedText={setSelectedTag}
                            />
                        </div>

                        {/* 선택된 항목에 따라 입력 폼 표시 */}
                        <div className="flex justify-center">
                            {selectedTag === "education" && (<EducationInfo educations={educations} />)}

                            {selectedTag === "career" && (<CareerInfo careers={careers} />)}

                            {selectedTag === "certification" && (<CertificationInfo certifications={certifications}/>)}

                            {selectedTag === "languageTest" && (<LanguageTest languageTests={languageTests}/>)}
                        </div>
                    </div>
                )}

                {checkedText === "coverLetter" && (
                    <div className="w-[950px] ">
                        <div className="ml-[1px] w-[998px] border-t border-gray-300 my-2" />
                        <Box >
                            <TextArea label={"성장 과정"} placeholder={"내용을 입력하세요"} disabled={true} value={coverLetter} setValue={setCoverLetter} size={"ml"} />

                            <TextArea label={"성격의 장단점"} placeholder={"내용을 입력하세요"} disabled={true} value={personality} setValue={setPersonality} size={"ml"} />

                            <TextArea label={"지원 동기"} placeholder={"내용을 입력하세요"} disabled={true} value={applicationMotivation} setValue={setApplicationMotivation} size={"ml"} />
                        </Box>

                        <div className="w-[998px] border bg-semi_theme text-theme border-gray-300 mb-[24px] pt-[24px]">
                            <TextArea label={"AI 피드백"} placeholder={"내용을 입력하세요"} disabled={true} value={coverLetterAiFeedback} setValue={setCoverLetterAiFeedback} size={"l"} />
                        </div>
                        <TextArea label={"컨설턴트 피드백"} placeholder={"내용을 입력하세요"} disabled={false} value={consultantCoverLetterFeedback} setValue={setConsultantCoverLetterFeedback} size={"m"} />


                    </div>
                )}

                {checkedText === "careerDescription" && (
                    <div className="w-[950px]">
                        <Box >
                            <TextArea label={"경력기술서 내용1"} placeholder={"내용을 입력하세요"} disabled={true} value={careerDescription} setValue={setCareerDescription} size={"ml"} />
                            <TextArea label={"경력기술서 내용2"} placeholder={"내용을 입력하세요"} disabled={true} value={careerDescription_2} setValue={setCareerDescription_2} size={"ml"} />
                        </Box>
                        <div className="ml-[1px] w-[998px] border-t border-gray-300 my-3" />
                        <div className="w-[998px] border bg-semi_theme text-theme border-gray-300 rounded-lg mb-[24px] pt-[24px]">
                            <TextArea label={"AI 피드백1"} placeholder={"내용을 입력하세요"} disabled={true} value={careerDescriptionAiFeedback} setValue={setCareerDescriptionAiFeedback} size={"l"} />
                            <TextArea label={"AI 피드백2"} placeholder={"내용을 입력하세요"} disabled={true} value={careerDescriptionAiFeedback_2} setValue={setCoverLetterAiFeedback_2} size={"ml"} />
                        </div>
                        <TextArea label={"컨설턴트 피드백1"} placeholder={"내용을 입력하세요"} disabled={false} value={consultantCareerDescriptionFeedback} setValue={setConsultantCareerDescriptionFeedback} size={"ml"} />

                    </div>
                )}

                <div className="flex justify-end mr-[34px]">
                    <Button
                        text="승인"
                        color="theme"
                        size="s"
                        disabled={false}
                        onClick={() => alert("승인됨")}
                        type="button"
                    />
                </div>

            </CommonModal>
        </>
    )

};


export default AiConsultingDetailModal;



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