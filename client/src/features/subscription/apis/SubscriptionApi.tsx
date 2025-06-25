
import { api } from "../../../common/Axios"

export const createSubscriptionApi = (paymentAmount : number, content:string , method : string) => {
    api(true).post("/subscriptions", {
        paymentAmount,
        content,
        method,

    })
} 