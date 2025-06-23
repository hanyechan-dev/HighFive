// 채용공고 관리 페이지에서 사용할 타입들 (백엔드 DTO 기반)
import type { JobPostingSummary } from "../../common/types/JobPostingTypes";

export interface JobPostingDetail {
  id: number;
  title: string;
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
}

export interface JobPostingCreateRequest {
  title: string;
  workingHours: string;
  workLocation: string;
  job: string;
  careerType: string;
  educationLevel: string;
  salary: number;
  content: string;
  requirement: string;
  companyType: string;
}

export interface JobPostingUpdateRequest {
  id: number;
  title: string;
  workingHours: string;
  workLocation: string;
  job: string;
  careerType: string;
  educationLevel: string;
  salary: number;
  content: string;
  requirement: string;
  companyType: string;
}

// 공통 타입을 다시 export
export type { JobPostingSummary }; 