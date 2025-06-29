import { api } from "../../../common/Axios";
import type { AiConsultingSummary, AiConsultingDetail } from "../props/AiConsultingProps";

interface AiConsultingListResponse {
  content: AiConsultingSummary[];
  totalElements: number;
  totalPages: number;
}

// AI 컨설팅 목록 조회 (페이지네이션)
export const AiConsultingListApi = (page: number, size: number) => {
  return api(true).get<AiConsultingListResponse>('/ai-consultings', {
    params: { page, size }
  });
};

// AI 컨설팅 승인
export const AiConsultingApprovalApi = (id: number) => {
  return api(true).post<void>('/ai-consultings/approval', { id });
};

// 편집 상세 조회
export const AiConsultingEditDetailApi = (id: number) => {
  return api(true).post<AiConsultingDetail>('/ai-consultings/edits/detail', { id });
};

// 피드백 상세 조회
export const AiConsultingFeedbackDetailApi = (id: number) => {
  return api(true).post<AiConsultingDetail>('/ai-consultings/feedbacks/detail', { id });
}; 