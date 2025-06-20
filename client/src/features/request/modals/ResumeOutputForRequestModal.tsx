import { useState } from "react";
import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import RadioButton from "../../../common/components/button/RadioButton";

import ModalTitle from "../../../common/components/title/ModalTitle";
import { resumeTypeEnum } from "../../../common/enum/Enum";
import BasicInfo from "../components/BasicInfo";
import CareerInfo from "../components/CareerInfo";
import EducationInfo from "../components/EducationInfo";
import CertificationInfo from "../components/CertificationInfo";
import LanguageTest from "../components/LanguageTestInfo";
import { useRequestController } from "../customHooks/useRequestController";



const ResumeOutputForRequestModal = () => {

    const {
        targetJob,
        targetCompanyName,
        setShowModalNumber,
        resume

    } = useRequestController();




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
                        {resume.educationResponseDtos.length > 0 && resume.educationResponseDtos.map((educationResponseDto) => (
                            <EducationInfo educationResponseDto={educationResponseDto} key={educationResponseDto.id} />
                        ))}
                    </ExternalBox>
                )}
                {clickedResume == "경력사항" && (
                    <ExternalBox>
                        {resume.careerResponseDtos.length > 0 && resume.careerResponseDtos.map((careerResponseDto) => (
                            <CareerInfo careerResponseDto={careerResponseDto} key={careerResponseDto.id} />
                        ))}
                    </ExternalBox>
                )}
                {clickedResume == "자격증" && (
                    <ExternalBox>
                        {resume.certificationResponseDtos.length > 0 && resume.certificationResponseDtos.map((certificationResponseDto) => (
                            <CertificationInfo certificationResponseDto={certificationResponseDto} key={certificationResponseDto.id} />
                        ))}
                    </ExternalBox>
                )}
                {clickedResume == "어학" && (
                    <ExternalBox>
                        {resume.languageTestResponseDtos.length > 0 && resume.languageTestResponseDtos.map((languageTestResponseDto) => (
                            <LanguageTest languageTestResponseDto={languageTestResponseDto} key={languageTestResponseDto.id} />
                        ))}
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

export default ResumeOutputForRequestModal