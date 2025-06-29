// AI 컨설팅 관련 타입들 (백엔드 DTO 기반)

export interface AiConsultingSummary {
  aiConsultingId: number;
  nickname: string;
  targetJob: string;
  targetCompanyName: string;
  requestedDate: string;
  consultingType: string;
}

export interface RequestForConsultantResponse {
  // 컨설턴트용 요청 정보 (필요시 추가)
  id: number;
  // 기타 필드들...
}

export interface AiConsultingResponse {
  // AI 컨설팅 정보 (필요시 추가)
  id: number;
  // 기타 필드들...
}

export interface AiConsultingDetail {
  requestForConsultantResponseDto: RequestForConsultantResponse;
  aiConsultingResponseDto: AiConsultingResponse;
} 