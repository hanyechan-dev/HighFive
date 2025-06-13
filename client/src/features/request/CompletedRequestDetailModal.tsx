import { useState } from "react";
import RadioButton from "../../common/components/button/RadioButton";
import ModalTitle from "../../common/components/title/ModalTitle";
import { documentTypeEnum, resumeTypeEnum } from "../../common/enum/Enum";
import CommonModal from "../../common/modals/CommonModal";
import TargetInfo from "./TargetInfo";
import { ExternalBox } from "../../common/components/box/Box";
import EducationInfo from "../../common/components/resume/EducationInfo";
import CareerInfo from "../../common/components/resume/CareerInfo";
import CertificationInfo from "../../common/components/resume/CertificationInfo";
import LanguageTest from "../../common/components/resume/LanguageTest";
import CareerDescriptionInfo from "../../common/components/careerDescription/CareerDescriptionInfo";
import AiConsulting from "../../common/components/aiConsulting/AiConsulting";
import CoverLetterInfo from "../../common/components/coverLetter/CoverLetterInfo";
import type { CareerDescriptionResponseDto, CoverLetterResponseDto, CompletedRequestDetailDto, ResumeJson } from "./RequestProps";
import ConsultantConsulting from "./ConsultantConsulting";



interface CompletedRequestDetailModalProps {
    completedRequestDetailDto: CompletedRequestDetailDto;
    onClose: () => void
}

const CompletedRequestDetailModal = ({ completedRequestDetailDto, onClose }: CompletedRequestDetailModalProps) => {


    const [checkedDocument, setCheckedDocument] = useState(documentTypeEnum[0].value);
    const [checkedResume, setCheckedResume] = useState(resumeTypeEnum[0].value);

    const requestResponseDto = completedRequestDetailDto.requestResponseDto;
    const aiConsultingResponseDto = completedRequestDetailDto.aiConsultingResponseDto;
    const consultantConsultingResponseDto = completedRequestDetailDto.consultantConsultingResponseDto;

    const { targetCompanyName, targetJob } = requestResponseDto;
    const resume = JSON.parse(requestResponseDto.resumeJson) as ResumeJson;
    const educationResponseDtos = resume.educationResponseDtos;
    const careerResponseDtos = resume.careerResponseDtos;
    const certificationResponseDtos = resume.certificationResponseDtos;
    const languageTestResponseDtos = resume.languageTestResponseDtos;
    const coverLetter = JSON.parse(requestResponseDto.coverLetterJson) as CoverLetterResponseDto;
    const careerDescription = JSON.parse(requestResponseDto.careerDescriptionJson) as CareerDescriptionResponseDto;

    
    const consultingType = requestResponseDto.consultingType

    const aiConsultingContentResponseDtos = aiConsultingResponseDto.aiConsultingContentResponseDtos

    const aiConsultingContentForCareerDescriptionResponseDtos = aiConsultingContentResponseDtos.filter(item => item.documentType === "경력기술서"); // 문서 타입 분할
    const aiConsultingContentForCoverLetterResponseDtos = aiConsultingContentResponseDtos.filter(item => item.documentType === "자기소개서"); // 문서 타입 분할

    const consultantConsultingContentResponseDtos = consultantConsultingResponseDto.consultantConsultingContentResponseDtos

    const consultantConsultingContentForCareerDescriptionResponseDtos = consultantConsultingContentResponseDtos.filter(item => item.documentType === "경력기술서"); // 문서 타입 분할
    const consultantConsultingContentForCoverLetterResponseDtos = consultantConsultingContentResponseDtos.filter(item => item.documentType === "자기소개서"); // 문서 타입 분할

    
    return (
        <CommonModal size="l" onClose={onClose}>
            <ModalTitle title={`${consultingType} 요청 상세`} />
            <TargetInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />
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
                    <CareerDescriptionInfo careerDescription={careerDescription} />
                    <AiConsulting aiConsultingContentResponseDtos={aiConsultingContentForCareerDescriptionResponseDtos} consultingType={consultingType} />
                    <ConsultantConsulting consultantConsultingContentResponseDtos={consultantConsultingContentForCareerDescriptionResponseDtos} consultingType={consultingType} />
                </>
            }
            {checkedDocument === "자기소개서" &&
                <>
                    <CoverLetterInfo coverLetter={coverLetter} />
                    <AiConsulting aiConsultingContentResponseDtos={aiConsultingContentForCoverLetterResponseDtos} consultingType={consultingType} />
                    <ConsultantConsulting consultantConsultingContentResponseDtos={consultantConsultingContentForCoverLetterResponseDtos} consultingType={consultingType} />
                </>
            }

        </CommonModal>
    );
};

export default CompletedRequestDetailModal;