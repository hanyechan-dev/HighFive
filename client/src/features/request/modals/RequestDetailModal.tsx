import { useState } from "react";
import AiConsulting from "../components/AiConsulting";
import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import RadioButton from "../../../common/components/button/RadioButton";
import CareerDescriptionInfo from "../components/CareerDescriptionInfo";
import CoverLetterInfo from "../components/CoverLetterInfo";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { documentTypeEnum, resumeTypeEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import BasicInfo from "../components/BasicInfo";
import CareerInfo from "../components/CareerInfo";
import CertificationInfo from "../components/CertificationInfo";
import EducationInfo from "../components/EducationInfo";
import LanguageTest from "../components/LanguageTestInfo";
import { parseStringToJson } from "../../../common/utils/JsonParseUtil";
import { useRequestController } from "../customHooks/useRequestController";




interface RequestDetailModalProps {
    onClick: () => void;
}

const RequestDetailModal = ({ onClick }: RequestDetailModalProps) => {

    const {
        requestDetailDto,
        setShowRequestDetailModal,
    } = useRequestController();

    const onClose = () => {
        setShowRequestDetailModal(false);
    }


    const {
        requestResponseDto: {
            targetCompanyName,
            targetJob,
            resumeJson,
            coverLetterJson,
            careerDescriptionJson,
            consultingType,
            requestStatus

        },
        aiConsultingResponseDto: {
            aiConsultingContentResponseDtos
        }
    } = requestDetailDto


    const [checkedDocument, setCheckedDocument] = useState(documentTypeEnum[0].value);
    const [checkedResume, setCheckedResume] = useState(resumeTypeEnum[0].value);

    const {
        educationResponseDtos,
        careerResponseDtos,
        certificationResponseDtos,
        languageTestResponseDtos,
        coverLetterResponseDto,
        careerDescriptionResponseDto
    }
        = parseStringToJson(resumeJson, careerDescriptionJson, coverLetterJson);

    const aiConsultingContentForCareerDescriptionResponseDtos = aiConsultingContentResponseDtos.filter(item => item.documentType === "경력기술서"); // 문서 타입 분할
    const aiConsultingContentForCoverLetterResponseDtos = aiConsultingContentResponseDtos.filter(item => item.documentType === "자기소개서"); // 문서 타입 분할


    return (
        <CommonModal size="l" onClose={onClose}>
            <ModalTitle title={`${consultingType} 요청 상세`} />
            <BasicInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />
            <div className="flex justify-start text-lg font-semibold text-gray-600 font-roboto ml-12">
                요청정보
            </div>
            <div className="border-b w-[952px] mt-2 ml-6"></div>
            <RadioButton name={""} textList={documentTypeEnum} checkedText={checkedDocument} setCheckedText={setCheckedDocument} size="document" />
            {checkedDocument === "이력서" &&
                <ExternalBox>
                    <RadioButton name={""} textList={resumeTypeEnum} checkedText={checkedResume} setCheckedText={setCheckedResume} size="internalResume" />
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
                                <LanguageTest languageTestResponseDto={languageTestResponseDto} key={languageTestResponseDto.id} />
                            ))}
                </ExternalBox>}

            {checkedDocument === "경력기술서" &&
                <>
                    <CareerDescriptionInfo careerDescriptionResponseDto={careerDescriptionResponseDto} />
                    <AiConsulting aiConsultingContentResponseDtos={aiConsultingContentForCareerDescriptionResponseDtos} consultingType={consultingType} />
                </>
            }
            {checkedDocument === "자기소개서" &&
                <>
                    <CoverLetterInfo coverLetterResponseDto={coverLetterResponseDto} />
                    <AiConsulting aiConsultingContentResponseDtos={aiConsultingContentForCoverLetterResponseDtos} consultingType={consultingType} />
                </>
            }

            <div className="flex justify-end mr-6">

                <Button color="theme" size="m" text="컨설턴트 컨설팅 요청" onClick={onClick} disabled={requestStatus !== "AI"} type={"button"} />
            </div>
        </CommonModal>
    );
};

export default RequestDetailModal;