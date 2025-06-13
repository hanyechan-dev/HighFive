import { useState } from "react";
import RadioButton from "../../common/components/button/RadioButton";
import CareerInfo from "../../common/components/resume/CareerInfo";
import CertificationInfo from "../../common/components/resume/CertificationInfo";
import EducationInfo from "../../common/components/resume/EducationInfo";
import LanguageTest from "../../common/components/resume/LanguageTest";
import ModalTitle from "../../common/components/title/ModalTitle";
import { ExternalBox } from "../../common/components/box/Box";
import Button from "../../common/components/button/Button";
import TargetInfo from "./TargetInfo";
import { resumeTypeEnum } from "../../common/enum/Enum";
import type { ResumeJson } from "./RequestProps";





interface ResumeOutputModalProps {
    targetJob: string;
    targetCompanyName: string;
    setShowModalNumber: (showModalNumber: number) => void
    resume: ResumeJson
}



const ResumeOutputModal = ({
    targetJob,
    targetCompanyName,
    setShowModalNumber,
    resume

}: ResumeOutputModalProps) => {
    const [clickedResume, setClickedResume] = useState(resumeTypeEnum[0].value);



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

            <div className="flex mt-4 ml-[-24px]">
                <RadioButton
                    name=""
                    textList={resumeTypeEnum}
                    checkedText={clickedResume}
                    setCheckedText={setClickedResume}
                    size="externalResume"
                />
            </div>

            <div className="flex">
                {clickedResume == "학력사항" && (
                    <ExternalBox>
                        <EducationInfo educationResponseDtos={resume.educationResponseDtos} />
                    </ExternalBox>
                )}
                {clickedResume == "경력사항" && (
                    <ExternalBox>
                        <CareerInfo careerResponseDtos={resume.careerResponseDtos} />
                    </ExternalBox>
                )}
                {clickedResume == "자격증" && (
                    <ExternalBox>
                        <CertificationInfo certificationResponseDtos={resume.certificationResponseDtos} />
                    </ExternalBox>
                )}
                {clickedResume == "어학" && (
                    <ExternalBox>
                        <LanguageTest languageTestResponseDtos={resume.languageTestResponseDtos} />
                    </ExternalBox>
                )}
            </div>


            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                <Button color={"theme"} size={"m"} disabled={false} text={"확인 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </>

    );

}

export default ResumeOutputModal