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

export interface EducationCreateDto {
    schoolName: string;
    educationLevel: string;
    major: string;
    gpa: string;
    location: string;
    enterDate: string;
    graduateDate: string;
}


export interface EducationUpdateDto {
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

export interface MyPageUpdateDto {
	phone: string;
	address: string;
}


export interface MemberUpdateDto {
	nickname: string;
}

export interface PasswordUpdateDto {
	password: string;
	newPassword: string;
	newPasswordCheck: string;
}

export interface ProposalSummaryForMemberDto {
	id: number;
	proposalTitle: string;
	companyName: string;
	proposalStatus: string;
	proposalDate: string;
}

export interface ProposalResponseDto {
	id: number;
	proposalTitle: string;
	companyName: string;
	proposalContent: string;
	proposalJob: string;
	proposalSalary: number;
	proposalDate: string;
	proposalStatus: string;
}

export interface ProposalUpdateDto {
	id: number;
	proposalStatus: string;
}

export interface ApplicationSummaryForMemberDto {
	id: number;
	title: string;
	companyName: string;
	job: string;
	isPassed: boolean;
	createdDate: string;
}

export interface ApplicationResponseDto {
	id: number;
	title: string;
	companyName: string;
	job: string;
	createdDate: string;
	isPassed: boolean;
	resumeJson: string;
	coverLetterJson: string;
	careerDescriptionJson: string;
}

export interface PaymentResponseDto {
	paymentId: number;	// 주문 번호
	id: number;	// 사용자 ID
    paymentAmount: number;	// 결제 금액
    content: string; // 결제 내역
    createdTime: string;  // 결제 시각
    method: string;
}