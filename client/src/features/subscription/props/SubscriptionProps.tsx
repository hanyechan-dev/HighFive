// 구독 관리 페이지에서 사용할 타입들 (백엔드 DTO 기반)

export interface SubscriptionResponse {
  id: number;
  name: string;
  userType: string;
  startDate: string;
  endDate: string;
  unsubscribeScheduled : boolean;
} 