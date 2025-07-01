
import dayjs from "dayjs"; //  타입스크립트 경고 문제 없음
import type { PaymentResponseDto } from "../props/myPageForMemberProps";

interface PaymentListItemProps {
    paymentResponseDto: PaymentResponseDto;
}

const defaultSetting = "grid grid-cols-4 items-center w-[1120px] border-b py-3 text-center font-roboto mx-[24px]"

const PaymentListItem = ({ paymentResponseDto }: PaymentListItemProps) => {
    return (

        <div className={defaultSetting}>
            <div>{paymentResponseDto.paymentAmount.toLocaleString()}원</div>
            <div>{paymentResponseDto.content}</div>
            <div>{paymentResponseDto.method}</div>
            <div>{dayjs(paymentResponseDto.createdTime).format('YYYY-MM-DD')}</div>
        </div>

    )
};

export default PaymentListItem;







