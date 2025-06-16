export interface JobPostingSummaryForMemberDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	job: string;
	workLocation: string;
	careerType: string;
	educationLevel: string;
	similarityScore: number;
	createdDate: string;
}

export interface JobPostingMainCardDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	job: string;
	workLocation: string;
	careerType: string;
	educationLevel: string;
	similarityScore: number;
	createdDate: string;
	imageUrl: string;
}

export interface JobPostingUnderCardDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	job: string;
	workLocation: string;
	careerType: string;
	educationLevel: string;
	similarityScore: number;
	createdDate: string;
	imageUrl: string;
}


export interface JobPostingForMemberResponseDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	workingHours: string;
	workLocation: string;
	job: string;
	careerType: string;
	educationLevel: string;
	salary: number;
	content: string;
	requirement: string;
	imageUrls: string[];
	createdDate: string;
	expiredDate: string;
}


export interface Resume {
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