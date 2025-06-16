import { useState } from "react";
import AiConsulting from "../components/AiConsulting";
import { ExternalBox } from "../../../common/components/box/Box";
import RadioButton from "../../../common/components/button/RadioButton";
import CareerDescriptionInfo from "../components/CareerDescriptionInfo";
import CoverLetterInfo from "../components/CoverLetterInfo";

import ModalTitle from "../../../common/components/title/ModalTitle";
import { documentTypeEnum, resumeTypeEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import ConsultantConsulting from "../components/ConsultantConsulting";
import BasicInfo from "../components/BasicInfo";
import CareerInfo from "../components/CareerInfo";
import CertificationInfo from "../components/CertificationInfo";
import EducationInfo from "../components/EducationInfo";
import LanguageTest from "../components/LanguageTest";
import { parseStringToJson } from "../../../common/utils/JsonParseUtil";
import { useRequestController } from "../customHooks/useRequestController";



const CompletedRequestDetailModal = () => {

    const {
        completedRequestDetailDto,
        setShowRequestDetailModal
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
            consultingType
        },
        aiConsultingResponseDto: {
            aiConsultingContentResponseDtos
        },
        consultantConsultingForMemberResponseDto: {
            consultantConsultingContentResponseDtos
        }
    } = completedRequestDetailDto



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

    const consultantConsultingContentForCareerDescriptionResponseDtos = consultantConsultingContentResponseDtos.filter(item => item.documentType === "경력기술서"); // 문서 타입 분할
    const consultantConsultingContentForCoverLetterResponseDtos = consultantConsultingContentResponseDtos.filter(item => item.documentType === "자기소개서"); // 문서 타입 분할


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
                    {checkedResume === "학력사항" && <EducationInfo educationResponseDtos={educationResponseDtos} />}
                    {checkedResume === "경력사항" && <CareerInfo careerResponseDtos={careerResponseDtos} />}
                    {checkedResume === "자격증" && <CertificationInfo certificationResponseDtos={certificationResponseDtos} />}
                    {checkedResume === "어학" && <LanguageTest languageTestResponseDtos={languageTestResponseDtos} />}
                </ExternalBox>}

            {checkedDocument === "경력기술서" &&
                <>
                    <CareerDescriptionInfo careerDescriptionResponseDto={careerDescriptionResponseDto} />
                    <AiConsulting aiConsultingContentResponseDtos={aiConsultingContentForCareerDescriptionResponseDtos} consultingType={consultingType} />
                    <ConsultantConsulting consultantConsultingContentResponseDtos={consultantConsultingContentForCareerDescriptionResponseDtos} consultingType={consultingType} />
                </>
            }
            {checkedDocument === "자기소개서" &&
                <>
                    <CoverLetterInfo coverLetterResponseDto={coverLetterResponseDto} />
                    <AiConsulting aiConsultingContentResponseDtos={aiConsultingContentForCoverLetterResponseDtos} consultingType={consultingType} />
                    <ConsultantConsulting consultantConsultingContentResponseDtos={consultantConsultingContentForCoverLetterResponseDtos} consultingType={consultingType} />
                </>
            }

        </CommonModal>
    );
};

export default CompletedRequestDetailModal;