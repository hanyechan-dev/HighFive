import { api } from "../../../common/Axios";
import type { JobPostingCreateRequest, JobPostingUpdateRequest } from "../props/JobPostingProps";

// 채용공고 목록 조회 (페이지네이션)
export const JobPostingListApi = (page: number, size: number) => {
  return api(true).get('/companies/jobPostings', {
    params: {
      page,
      size
    },
  });
};

// 채용공고 상세 조회
export const JobPostingDetailApi = (id: number) => {
  return api(true).post('/companies/jobPostings/detail', { id });
};

// 채용공고 생성 (FormData 사용 - 이미지 업로드 포함)
export const JobPostingCreateApi = (data: JobPostingCreateRequest, images?: File[]) => {
  const formData = new FormData();
  
  // 기본 데이터 추가
  Object.entries(data).forEach(([key, value]) => {
    formData.append(key, value.toString());
  });
  
  // 이미지 파일들 추가
  if (images && images.length > 0) {
    images.forEach((image, index) => {
      formData.append(`jobPostingImageCreateListDto.jobPostingImageCreateDtos[${index}].image`, image);
    });
  }
  
  return api(true).post('/companies/jobPostings', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

// 채용공고 수정 (FormData 사용 - 이미지 업로드 포함)
export const JobPostingUpdateApi = (data: JobPostingUpdateRequest, images?: File[]) => {
  const formData = new FormData();
  
  // 기본 데이터 추가
  Object.entries(data).forEach(([key, value]) => {
    formData.append(key, value.toString());
  });
  
  // 이미지 파일들 추가
  if (images && images.length > 0) {
    images.forEach((image, index) => {
      formData.append(`jobPostingImageCreateListDto.jobPostingImageCreateDtos[${index}].image`, image);
    });
  }
  
  return api(true).put('/companies/jobPostings', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

// 채용공고 삭제
export const JobPostingDeleteApi = (id: number) => {
  return api(true).post('/companies/jobPostings/deletion', { id });
};

// 채용공고 상태 변경 (진행중/마감)
export const JobPostingStatusUpdateApi = (id: number, status: string) => {
  return api(true).patch(`/companies/job-postings/${id}/status`, { status });
}; 