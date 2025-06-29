import { apiForm } from "../../../common/Axios";
import { api } from "../../../common/Axios";
import type { JobPostingCreateRequest, JobPostingUpdateRequest, JobPostingSummary, JobPostingDetail } from "../props/JobPostingProps";

interface JobPostingListResponse {
  content: JobPostingSummary[];
  totalElements: number;
  totalPages: number;
}

// 채용공고 목록 조회 (페이지네이션)
export const JobPostingListApi = (page: number, size: number) => {
  return api(true).get<JobPostingListResponse>('/companies/jobPostings', {
    params: { page, size }
  });
};

// 채용공고 상세 조회
export const JobPostingDetailApi = (id: number) => {
  return api(true).post<JobPostingDetail>('/companies/jobPostings/detail', { id });
};

// 채용공고 생성 (FormData 사용 - 이미지 업로드 포함)
export const JobPostingCreateApi = (data: JobPostingCreateRequest, images?: File[]) => {
  const formData = new FormData();
  
  // JobPostingCreateDto 데이터 추가
  Object.entries(data).forEach(([key, value]) => {
    formData.append(key, value.toString());
  });
  
  // JobPostingImageCreateListDto 데이터 추가
  if (images && images.length > 0) {
    images.forEach((image, index) => {
      formData.append(`jobPostingImageCreateListDto.jobPostingImageCreateDtos[${index}].image`, image);
    });
  }
  
  return apiForm(true).post('/companies/jobPostings', formData);
};

// 채용공고 수정 (FormData 사용 - 이미지 업로드 포함)
export const JobPostingUpdateApi = (data: JobPostingUpdateRequest, images?: File[]) => {
  const formData = new FormData();
  
  // JobPostingUpdateDto 데이터 추가
  Object.entries(data).forEach(([key, value]) => {
    formData.append(key, value.toString());
  });
  
  // JobPostingImageCreateListDto 데이터 추가
  if (images && images.length > 0) {
    images.forEach((image, index) => {
      formData.append(`jobPostingImageCreateListDto.jobPostingImageCreateDtos[${index}].image`, image);
    });
  }
  
  return apiForm(true).put('/companies/jobPostings', formData);
};

// 채용공고 삭제
export const JobPostingDeleteApi = (id: number) => {
  return api(true).post<void>('/companies/jobPostings/deletion', { id });
}; 