import { api } from "../../common/Axios.tsx";
import type { MemberFilter } from "./MemberPoolSlice.tsx";

export interface MemberPoolSummary {
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
    startDate: string;
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

export const MemberPoolSummaryApi = (
    filter: MemberFilter,
    page: number,
) => {
    return api(true).post<MemberPoolSummary[]>('/companies/members', {
        hasCareer: filter.hasCareer,
        educationLevel: filter.educationLevel,
        address: filter.address,
        job: filter.job
    },
        {
            params: {
                page,
                size: 10
            },
        });
};

export const MemberPoolDetailApi = (id: number) => {
    return api(false).post<MemberPoolDetail>('/companies/members', {
        id
    });
};