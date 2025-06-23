import { api } from "../../../common/Axios";
import type { SubscriptionResponse } from "../props/SubscriptionProps";

// 구독 정보 조회
export const SubscriptionApi = () => {
  return api(true).get<SubscriptionResponse>('/companies/subscriptions');
}; 