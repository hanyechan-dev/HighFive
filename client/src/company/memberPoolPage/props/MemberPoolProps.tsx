export interface MemberPoolSummary{
    id: number;
    name: string;
    genderType: string;
    birthDate: string;
    job: string;
    hasCareer: boolean;
    similarityScore: number;
    educationLevel: string;
}

export interface MemberPoolDetail {
    name: string;
    email: string;
    genderType: string;
    birthDate: string;
    hasCareer: boolean;
    job: string;
    phone: string;
    educationResponseDto?: EducationResponseDto;
    careerResponseDto?: CareerResponseDto;
    certificationResponseDto?: CertificationResponseDto[];
    languageTestResponseDto?: LanguageTestResponseDto[];
}

export interface EducationResponseDto {
    id: number;
    schoolName: string;
    educationLevel: string;
    major: string;
    gpa?: number;
    location?: string;
    enterDate?: string;
    graduateDate?: string;
}

export interface CareerResponseDto {
    id: number;
    companyName: string;
    job: string;
    department?: string;
    position?: string;
    startDate?: string;
    endDate?: string;
}

export interface CertificationResponseDto {
    id: number;
    certificationName: string;
    issuingOrg?: string;
    grade?: string;
    score?: string;
    certificationNo?: string;
    acquisitionDate?: string;
}

export interface LanguageTestResponseDto {
    id: number;
    languageType: string;
    testName: string;
    issuingOrg?: string;
    grade?: string;
    score?: string;
    certificationNo?: string;
    acquisitionDate?: string;
}

export interface ProposalCreateDto {
    memberId: number;
    proposalTitle: string;
    proposalContent: string;
    proposalJob: string;
    proposalSalary: number;
}