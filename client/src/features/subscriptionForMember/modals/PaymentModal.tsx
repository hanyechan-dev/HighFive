import { useState } from "react";
import Button from "../../../common/components/button/Button";

interface PaymentModalProps {
    paymentAmount: number;
    onClick: () => void;
    onClose: (showPaymentModal: boolean) => void;
}

const PaymentModal = ({ paymentAmount, onClick, onClose }: PaymentModalProps) => {
    const [isClosing, setIsClosing] = useState(false);

    const now = new Date();
    now.setMonth(now.getMonth() + 1);
    const oneMonthLater = now.toISOString().slice(0, 10);

    const handleClose = () => {
        setIsClosing(true);
        setTimeout(() => {
            onClose(false);
        }, 300); // 애니메이션 끝난 후 unmount
    };

    return (
        <div className="fixed inset-0 bg-black/50 z-50 flex items-center justify-center">
            <div
                className="overflow-y-auto overflow-x-hidden bg-white rounded-lg w-[512px] font-roboto"
                style={{
                    animation: `${isClosing ? "popOut" : "popIn"} 300ms ease-out forwards`,
                }}
            >
                <div className="h-[150px] bg-gradient-to-r from-theme/70 to-theme py-[40px] px-[40px] mb-6">
                    <div className="text-white text-3xl font-bold mb-4">결제</div>
                    <div className="text-white">안전하고 편리한 결제를 시작하세요</div>
                </div>
                <div className="border border-gray-300 w-[464px] mx-auto rounded-2xl bg-gray-100 px-6 py-6 mb-6">
                    <div className="font-bold text-2xl text-gray-800 mb-6">주문 정보</div>
                    <div className="border-b border-gray-300 mb-6"></div>
                    <div className="flex justify-between mb-6">
                        <div className="text-xl text-gray-700">상품명</div>
                        <div className="text-xl font-semibold text-gray-700">프리미엄 구독</div>
                    </div>
                    <div className="flex justify-between mb-6">
                        <div className="text-xl text-gray-700">결제주기</div>
                        <div className="text-xl font-semibold text-gray-700">월간</div>
                    </div>
                    <div className="flex justify-between mb-6">
                        <div className="text-xl text-gray-700">다음 결제일</div>
                        <div className="text-xl font-semibold text-gray-700">{oneMonthLater}</div>
                    </div>
                    <div className="border-b border-gray-300 mb-6"></div>
                    <div className="flex justify-between mb-6">
                        <div className="text-2xl text-gray-900 font-bold">총 결제금액</div>
                        <div className="text-2xl font-bold text-theme">{paymentAmount}원</div>
                    </div>
                </div>
                <div className="flex justify-between mr-6">
                    <Button
                        color={"white"}
                        size={"s"}
                        disabled={false}
                        text={"취소"}
                        type={"button"}
                        onClick={handleClose}
                    />
                    <Button
                        color={"theme"}
                        size={"s"}
                        disabled={false}
                        text={"결제 진행"}
                        type={"button"}
                        onClick={onClick}
                    />
                </div>
            </div>
        </div>
    );
};

export default PaymentModal;