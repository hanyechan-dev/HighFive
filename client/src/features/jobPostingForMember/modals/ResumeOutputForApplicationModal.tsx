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
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";

interface ResumeOutputForApplicationModal {
    isShowModalNumber0Loading: boolean
}


const ResumeOutputForApplicationModal = ({ isShowModalNumber0Loading }: ResumeOutputForApplicationModal) => {

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
            {isShowModalNumber0Loading ? (<LoadingSpinner message="이력서를 불러오는 중..." size="lg" color="theme" />) : (
                <>
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
                        <Button color={"theme"} size={"m"} disabled={false} text={"확인 및 다음"} type={"button"} onClick={onClickNext} />
                    </div>
                </>
            )}
        </>

    );

}

export default ResumeOutputForApplicationModal

