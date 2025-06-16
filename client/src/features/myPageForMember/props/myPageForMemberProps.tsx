export interface EducationResponseDto {
    id: number;
    schoolName: string;
    educationLevel: string;
    major: string;
    gpa: number;
    location: string;
    enterDate: string;
    graduateDate: string;
}

export interface EducationCreateDto {
    schoolName: string;
    educationLevel: string;
    major: string;
    gpa: number;
    location: string;
    enterDate: string;
    graduateDate: string;
}


export interface EducationUpdateDto {
    id: number;
    schoolName: string;
    educationLevel: string;
    major: string;
    gpa: number;
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

export interface CareerCreateDto {
    companyName: string;
    job: string;
    department: string;
    position: string;
    startDate: string;
    endDate: string;
}

export interface CareerUpdateDto {
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

export interface CertificationCreateDto {

	certificationName: string;
	issuingOrg: string;
	grade: string;
	score: string;
	certificationNo: string;
	acquisitionDate: string;
}

export interface CertificationUpdateDto {
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

export interface LanguageTestCreateDto {
	languageType: string;
	testName: string;
	issuingOrg: string;
	grade: string;
	score: string;
	certificationNo: string;
	acquisitionDate: string;
}

export interface LanguageTestUpdateDto {
	id: number;
	languageType: string;
	testName: string;
	issuingOrg: string;
	grade: string;
	score: string;
	certificationNo: string;
	acquisitionDate: string;
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

export interface CareerDescriptionCreateDto {
	title: string;
	contents: CareerDescriptionContentCreateDto[];
}

export interface CareerDescriptionContentCreateDto {
	item: string;
	content: string;
}

export interface CareerDescriptionUpdateDto {
	id: number;
	title: string;
	contents: CareerDescriptionContentUpdateDto[];
}

export interface CareerDescriptionContentUpdateDto {
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

export interface CoverLetterCreateDto {
	title: string;
	contents: CoverLetterContentCreateDto[];
}

export interface CoverLetterContentCreateDto {
	item: string;
	content: string;
}

export interface CoverLetterUpdateDto {
	id: number;
	title: string;
	contents: CoverLetterContentUpdateDto[];
}

export interface CoverLetterContentUpdateDto {
	id: number;
	item: string;
	content: string;
}

export interface MemberMyPageResponseDto {
	memberResponseDto: MemberResponseDto;
	myPageResponseDto: MyPageResponseDto;
}

export interface MemberResponseDto {
	nickname: string;
}

export interface MyPageResponseDto {
	email: string;
	name: string;
	birthDate: string;
	genderType: string;
	phone: string;
	address: string;
	type: string;
}