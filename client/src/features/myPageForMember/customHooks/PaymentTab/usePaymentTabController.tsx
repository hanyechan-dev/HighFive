import type { PaymentResponseDto } from "../../props/myPageForMemberProps";
import { usePaymentTabContext } from "./usePaymentTabContext";




export const usePaymentTabController = () => {
    const { state, dispatch } = usePaymentTabContext();

    const paymentResponseDtos = state.paymentResponseDtos;
    const setPaymentResponseDtos = (paymentResponseDtos: PaymentResponseDto[]) => {
        dispatch({ type: "SET_PAYMENT_RESPONSE_DTOS", payload: paymentResponseDtos });
    };
    
    const totalElements = state.totalElements;
    const setTotalElements = (totalElements: number) => {
        dispatch({ type: "SET_TOTAL_ELEMENTS", payload: totalElements });
    };


    return {
        paymentResponseDtos,
        setPaymentResponseDtos,
        totalElements,
        setTotalElements,
    };


}