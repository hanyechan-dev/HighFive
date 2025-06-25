import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import { readMyPaymentsApi } from "../../apis/MyPageForMemberApi";
import type { PaymentResponseDto } from "../../props/myPageForMemberProps";
import { usePaymentTabController } from "./usePaymentTabController";

export const usePaymentTabApi = () => {
    const { 
        setPaymentResponseDtos,
        setTotalElements
    } = usePaymentTabController();

    const readPayments = async (page: number, size: number) => {
        try {

            const response = await readMyPaymentsApi(page, size);
            const paymentResponseDtos: PaymentResponseDto[] = response.data.content;
            setPaymentResponseDtos(paymentResponseDtos);
            setTotalElements(response.data.totalElements);
        } catch (err) {
            printErrorInfo(err);
        }

    }

    return {
        readPayments
    }


}