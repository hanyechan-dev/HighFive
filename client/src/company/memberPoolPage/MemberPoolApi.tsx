import { Request } from '../api/Request';
import type { MemberFilter } from "./MemberPoolSlice.tsx";

export interface MemberPoolSummary{
    id: number;
    name: string;
    job: string;
    hasCareer: boolean;
    similarityScore: number;
    educationLevel: string;
}

export interface MemberPoolDetail {
    name: string;
    email: string;
    gender: string;
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

export const MemberPoolPageApi = (
    filter: MemberFilter,
    page: number,
) => {
    return Request.post<MemberPoolSummary[]>('/companies/members', {
        hasCareer: filter.hasCareer,
        educationLevel: filter.educationLevel,
        address: filter.address,
        job: filter.job,
        page,
        size: 10
    });
};

export const MemberPoolDetailApi = (id: number) => {
    return Request.post<MemberPoolDetail>('/companies/members/detail', { id });
};

export const createProposalApi = (data: ProposalCreateDto) => {
    return Request.post<void>('/companies/proposals', data);
};