import type { EducationResponseDto, CareerResponseDto, CertificationResponseDto, LanguageTestResponseDto } from "../../myPageForMember/props/myPageForMemberProps";

export interface RequestResponseDto {
	id: number;
	targetJob: string;
	targetCompanyName: string;
	consultingType: string;
	requestStatus: string;
	createdDate: string;
	resumeJson: string;
	coverLetterJson: string;
	careerDescriptionJson: string;
}

export interface ConsultantConsultingResponseDto {
	consultingType: string;
	createdDate: string; // 승인날짜
	completedDate: string; // 완료날짜   
	consultantConsultingContentResponseDtos: ConsultantConsultingContentResponseDto[];
}

export interface ConsultantConsultingContentResponseDto {
	id: number;
	documentType: string;
	item: string;
	content: string;
}

export interface AiConsultingResponseDto {
	consultingType: string;
	aiConsultingContentResponseDtos: AiConsultingContentResponseDto[];
}

export interface AiConsultingContentResponseDto {
	id: number;
	documentType: string;
	item: string;
	content: string;
}

export interface RequestSummaryDto {
	id: number;
	targetJob: string;
	targetCompanyName: string;
	requestStatus: string;
	createdDate: string;
}

export interface RequestDetailDto {
	requestResponseDto: RequestResponseDto;
	aiConsultingResponseDto: AiConsultingResponseDto;
}


export interface CompletedRequestDetailDto {
	requestResponseDto: RequestResponseDto;
	aiConsultingResponseDto: AiConsultingResponseDto;
	consultantConsultingForMemberResponseDto: ConsultantConsultingForMemberResponseDto;
}

export interface Resume {
	educationResponseDtos: EducationResponseDto[];
	careerResponseDtos: CareerResponseDto[];
	certificationResponseDtos: CertificationResponseDto[];
	languageTestResponseDtos: LanguageTestResponseDto[];
}

export interface RequestForConsultantResponseDto {
    id: number;
    targetJob: string;
    targetCompanyName: string;
    consultingType: string;
    requestStatus: string;
    createdDate: string;
    resumeJson: string;
    coverLetterJson: string;
    careerDescriptionJson: string;
    nickname: string;
}

export interface ConsultantConsultingForMemberResponseDto {
    consultingType: string;
    createdDate: string; // 승인날짜
    completedDate: string; // 완료날짜
    name: string;
    consultantConsultingContentResponseDtos: ConsultantConsultingContentResponseDto[];
}

export interface AiConsultingDetailResponseDto {
    requestForConsultantResponseDto: RequestForConsultantResponseDto;
    aiConsultingResponseDto: AiConsultingResponseDto;
}