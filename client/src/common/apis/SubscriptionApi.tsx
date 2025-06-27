import { api } from "../Axios";
import type { SubscriptionResponse } from "../../features/subscription/props/SubscriptionProps";

export const SubscriptionApi = () => {
    return api(true).get<SubscriptionResponse>('/subscriptions/me');
};

export const createSubscriptionApi = (paymentAmount: number, content: string, method: string) => {
    return api(true).post("/subscriptions", {
        paymentAmount,
        content,
        method,
    })
}

export const scheduleUnsubscribeApi = () =>{
    return api(true).put("/subscriptions");
}