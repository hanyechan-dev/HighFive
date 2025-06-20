

import type { PaymentResponseDto } from "../../props/myPageForMemberProps";



export interface PaymentTabState {
    paymentResponseDtos : PaymentResponseDto[];
    totalElements : number;
}


export type PaymentTabAction =
    | { type: "SET_PAYMENT_RESPONSE_DTOS"; payload: PaymentResponseDto[] }
    | { type: "SET_TOTAL_ELEMENTS"; payload: number };
