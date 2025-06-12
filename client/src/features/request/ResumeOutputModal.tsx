import { useState } from "react";
import RadioButton from "../../common/components/button/RadioButton";
import CareerInfo from "../../common/components/resume/CareerInfo";
import CertificationInfo from "../../common/components/resume/CertificationInfo";
import EducationInfo from "../../common/components/resume/EducationInfo";
import LanguageTest from "../../common/components/resume/LanguageTest";
import ModalTitle from "../../common/components/title/ModalTitle";
import { ExternalBox } from "../../common/components/box/Box";
import type { resumeProps } from "../../common/props/AiConsultingProps";
import Button from "../../common/components/button/Button";
import TargetInfo from "./TargetInfo";




const resumeTextList = [
    { label: "학력 사항", value: "education" },
    { label: "경력 사항", value: "career" },
    { label: "자격증", value: "certification" },
    { label: "어학", value: "languageTest" },
]



interface ResumeOutputModalProps {
    targetJob: string;
    targetCompanyName: string;
    setShowModalNumber: (showModalNumber: number) => void
    resume: resumeProps
}



const ResumeOutputModal = ({
    targetJob,
    targetCompanyName,
    setShowModalNumber,
    resume

}: ResumeOutputModalProps) => {
    const [selectedTag, setSelectedTag] = useState(resumeTextList[0].value);



    const onClickNext = () => {
        setShowModalNumber(2);
    }

    const onClickPrev = () => {
        setShowModalNumber(0);
    }

    return (
        <>
            <ModalTitle title="이력서" />
            <TargetInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />
            <div>
                <div className="flex gap-[24px] justify-center ml-[-80px] mt-4">
                    <RadioButton
                        name=""
                        textList={resumeTextList}
                        checkedText={selectedTag}
                        setCheckedText={setSelectedTag}
                    />
                </div>

                <div className="flex justify-center">
                    {selectedTag == "education" && (
                        <ExternalBox>
                            <EducationInfo educations={resume.educations} />
                        </ExternalBox>
                    )}
                    {selectedTag == "career" && (
                        <ExternalBox>
                            <CareerInfo careers={resume.careers} />
                        </ExternalBox>
                    )}
                    {selectedTag == "certification" && (
                        <ExternalBox>
                            <CertificationInfo certifications={resume.certifications} />
                        </ExternalBox>
                    )}
                    {selectedTag == "languageTest" && (
                        <ExternalBox>
                            <LanguageTest languageTests={resume.languageTests} />
                        </ExternalBox>
                    )}
                </div>
            </div>

            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                <Button color={"theme"} size={"m"} disabled={false} text={"확인 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </>

    );

}

export default ResumeOutputModal