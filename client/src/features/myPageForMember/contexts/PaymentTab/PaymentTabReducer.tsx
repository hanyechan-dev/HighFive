import type { PaymentTabAction, PaymentTabState } from "./PaymentTabTypes";

export const initialState: PaymentTabState = {
    paymentResponseDtos : [],
    totalElements : 0
}


export const reducer = (state: PaymentTabState, action: PaymentTabAction): PaymentTabState => {
    switch (action.type) {
        case "SET_PAYMENT_RESPONSE_DTOS":
            return { ...state, paymentResponseDtos: action.payload };
        case "SET_TOTAL_ELEMENTS":
            return { ...state, totalElements: action.payload };
        default:
            return state;
    }
};
