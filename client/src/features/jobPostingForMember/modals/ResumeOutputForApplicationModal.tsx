import { useState } from "react";
import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import RadioButton from "../../../common/components/button/RadioButton";

import ModalTitle from "../../../common/components/title/ModalTitle";
import { resumeTypeEnum } from "../../../common/enum/Enum";
import EducationInfo from "../../request/components/EducationInfo";
import CareerInfo from "../../request/components/CareerInfo";
import CertificationInfo from "../../request/components/CertificationInfo";
import LanguageTest from "../../request/components/LanguageTestInfo";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";


const ResumeOutputForApplicationModal = () => {

    const {
        setShowModalNumber,
        resume
    } = useJobPostingForMemberController();




    const [clickedResume, setClickedResume] = useState(resumeTypeEnum[0].value);



    const onClickNext = () => {
        setShowModalNumber(1);
    }


    return (
        <>
            <ModalTitle title="이력서" />

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
                <Button color={"theme"} size={"m"} disabled={false} text={"확인 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </>

    );

}

export default ResumeOutputForApplicationModal

