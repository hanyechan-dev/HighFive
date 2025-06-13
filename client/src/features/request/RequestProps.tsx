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
	consultantConsultingResponseDto: ConsultantConsultingResponseDto;
}

export interface EducationResponseDto {
	id: number;
	schoolName: string;
	educationLevel: string;
	major: string;
	gpa: string;
	location: string;
	enterDate: string;
	graduateDate: string;
}

export interface CareerResponseDto {
	id: number;
	companyName: string;
	job: string;
	department: string;
	position: string;
	startDate: string;
	endDate: string;
}

export interface CertificationResponseDto {
	id: number;
	certificationName: string;
	issuingOrg: string;
	grade: string;
	score: string;
	certificationNo: string;
	acquisitionDate: string;
}

export interface LanguageTestResponseDto {
	id: number;
	languageType: string;
	testName: string;
	issuingOrg: string;
	grade: string;
	score: string;
	certificationNo: string;
	acquisitionDate: string;
}

export interface ResumeJson {
	educationResponseDtos: EducationResponseDto[];
	careerResponseDtos: CareerResponseDto[];
	certificationResponseDtos: CertificationResponseDto[];
	languageTestResponseDtos: LanguageTestResponseDto[];
}

export interface CareerDescriptionSummaryDto {
	id: number;	
	title: string;
	createdDate: string;
}

export interface CareerDescriptionResponseDto {
	id: number;
	title: string;
	contents: CareerDescriptionContentResponseDto[];
}

export interface CareerDescriptionContentResponseDto {
	id: number;
	item: string;
	content: string;
}

export interface CoverLetterSummaryDto {
	id: number;	
	title: string;
	createdDate: string;
}

export interface CoverLetterResponseDto {
	id: number;
	title: string;
	contents: CoverLetterContentResponseDto[];
}

export interface CoverLetterContentResponseDto {
	id: number;
	item: string;
	content: string;
}
