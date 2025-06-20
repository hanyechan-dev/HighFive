import { useState } from "react";
import { ExternalBox } from "../../../common/components/box/Box";
import RadioButton from "../../../common/components/button/RadioButton";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { documentTypeEnum, resumeTypeEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import type { ApplicationResponseDto } from "../props/myPageForMemberProps";
import EducationInfo from "../../request/components/EducationInfo";
import { parseStringToJson } from "../../../common/utils/JsonParseUtil";
import CareerInfo from "../../request/components/CareerInfo";
import CertificationInfo from "../../request/components/CertificationInfo";
import LanguageTestInfo from "../../request/components/LanguageTestInfo";
import CareerDescriptionInfo from "../../request/components/CareerDescriptionInfo";
import CoverLetterInfo from "../../request/components/CoverLetterInfo";
import BuildingIcon from "../../../common/icons/BuildingIcon";
import { CheckCircleIcon, HourglassIcon, MegaphoneIcon } from "lucide-react";
import JobIcon from "../../../common/icons/JobIcon";
import CalenderIcon from "../../../common/icons/CalenderIcon";


interface ApplicationDetailModalProps {
    applicationResponseDto: ApplicationResponseDto;
    onClose: () => void;
}


const ApplicationDetailModal = ({ applicationResponseDto, onClose }: ApplicationDetailModalProps) => {

    const { resumeJson, coverLetterJson, careerDescriptionJson } = applicationResponseDto;
    const [checkedDocument, setCheckedDocument] = useState(documentTypeEnum[0].value);
    const [checkedResume, setCheckedResume] = useState(resumeTypeEnum[0].value);

    const {
        educationResponseDtos,
        careerResponseDtos,
        certificationResponseDtos,
        languageTestResponseDtos,
        careerDescriptionResponseDto,
        coverLetterResponseDto
    } = parseStringToJson(resumeJson, careerDescriptionJson, coverLetterJson);


    return (
        <CommonModal size="l" onClose={onClose}>
            <ModalTitle title="지원 상세" />

            <div className="flex gap-[750px]">
                <div className="flex ml-6 mb-3">
                    <BuildingIcon />
                    <div className="ml-3 font-roboto">{applicationResponseDto.companyName}</div>
                </div>
                <div className="flex ml-6 mb-3">
                    {applicationResponseDto.isPassed ? (
                        <>
                            <CheckCircleIcon className="size-5" color="#008000" />
                            <div className="ml-3 font-roboto">합격</div>
                        </>
                    ) : (
                        <>
                            <HourglassIcon className="size-5" color="#444" />
                            <div className="ml-3 font-roboto">대기</div>
                        </>
                    )}
                </div>
            </div>

            <div className="flex gap-[650px]">
                <div className="flex ml-6 mb-3">
                    <MegaphoneIcon className="size-5" color="#444" />
                    <div className="ml-3 font-roboto">{applicationResponseDto.title}</div>
                </div>

                <div className="flex ml-6 mb-3">
                    <CalenderIcon />
                    <div className="ml-3 font-roboto">{new Date(applicationResponseDto.createdDate).toLocaleDateString()}</div>
                </div>
            </div>


            <div className="flex ml-6 mb-3">
                <JobIcon />
                <div className="ml-3 font-roboto">{applicationResponseDto.job}</div>
            </div>






            <RadioButton size="document" name={""} textList={documentTypeEnum} checkedText={checkedDocument} setCheckedText={setCheckedDocument} />
            <ExternalBox>
                {checkedDocument === "이력서" && (
                    <>
                        <div className="mt-[-24px]">
                            <RadioButton size="internalResume" name={""} textList={resumeTypeEnum} checkedText={checkedResume} setCheckedText={setCheckedResume} />
                        </div>
                        {checkedResume === "학력사항" &&
                            educationResponseDtos.length > 0 && educationResponseDtos.map((educationResponseDto) => (
                                <EducationInfo educationResponseDto={educationResponseDto} key={educationResponseDto.id} />
                            ))}
                        {checkedResume === "경력사항" &&
                            careerResponseDtos.length > 0 && careerResponseDtos.map((careerResponseDto) => (
                                <CareerInfo careerResponseDto={careerResponseDto} key={careerResponseDto.id} />
                            ))}
                        {checkedResume === "자격증" &&
                            certificationResponseDtos.length > 0 && certificationResponseDtos.map((certificationResponseDto) => (
                                <CertificationInfo certificationResponseDto={certificationResponseDto} key={certificationResponseDto.id} />
                            ))}
                        {checkedResume === "어학" &&
                            languageTestResponseDtos.length > 0 && languageTestResponseDtos.map((languageTestResponseDto) => (
                                <LanguageTestInfo languageTestResponseDto={languageTestResponseDto} key={languageTestResponseDto.id} />
                            ))}
                    </>
                )}
                {checkedDocument === "경력기술서" && (
                    <>
                        <CareerDescriptionInfo careerDescriptionResponseDto={careerDescriptionResponseDto} />
                    </>
                )}
                {checkedDocument === "자기소개서" && (
                    <>
                        <CoverLetterInfo coverLetterResponseDto={coverLetterResponseDto} />
                    </>
                )}
            </ExternalBox>
        </CommonModal>
    )
}

export default ApplicationDetailModal;
