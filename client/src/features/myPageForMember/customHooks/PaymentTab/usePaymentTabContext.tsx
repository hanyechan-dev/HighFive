import { useContext } from "react";
import { PaymentTabContext } from "../../contexts/PaymentTab/PaymentTabContext";



export const usePaymentTabContext = () => {
    const context = useContext(PaymentTabContext);
    if (!context) throw new Error("MyPageForMemberPageContext는 Provider 안에서 사용해야 함");
    return context;
};
