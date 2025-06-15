import { useState } from "react";
import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import RadioButton from "../../../common/components/button/RadioButton";

import ModalTitle from "../../../common/components/title/ModalTitle";
import { resumeTypeEnum } from "../../../common/enum/Enum";
import type { Resume } from "../props/RequestProps";
import BasicInfo from "../components/BasicInfo";
import CareerInfo from "../components/CareerInfo";
import EducationInfo from "../components/EducationInfo";
import CertificationInfo from "../components/CertificationInfo";
import LanguageTest from "../components/LanguageTest";





interface ResumeOutputModalProps {
    resumeOutputModalProps: {
        targetJob: string;
        targetCompanyName: string;
        setShowModalNumber: (showModalNumber: number) => void
        resume: Resume
    }
}



const ResumeOutputModal = ({resumeOutputModalProps:{
    targetJob,
    targetCompanyName,
    setShowModalNumber,
    resume

}}: ResumeOutputModalProps) => {
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
            <BasicInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />

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