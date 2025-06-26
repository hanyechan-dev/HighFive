import { CheckIcon, CreditCardIcon } from "lucide-react";
import PeopleIcon from "../../subscription/components/PeopleIcon";
import CommonPage from "../../../common/pages/CommonPage";
import DocumentTextIcon from "../../subscription/components/DocumentTextIcon";
import { useState } from "react";
import PaymentModal from "../modals/PaymentModal";
import PageTitle from "../../../common/components/title/PageTitle";
import { createSubscriptionApi } from "../../../common/apis/SubscriptionApi";
import { useDispatch } from "react-redux";
import { setToken } from "../../auth/slices/AuthSlice";

const SubscriptionPlansForMemberPage = () => {
    const dispatch = useDispatch();

    const [selectedPlan, setSelectedPlan] = useState({
        name: "프리미엄 구독",
        price: 9800,
        period: "월"
    });

    const [showPaymentModal, setShowPaymentModal] = useState(false);

    const onClickSubscriptionButton = () => {
        setShowPaymentModal(true);
    }

    const onClickPaymentButton = async () => {
        const res = await createSubscriptionApi(selectedPlan.price, selectedPlan.name, "카카오페이");
        const { accessToken, refreshToken } = res.data;
        dispatch(setToken({ accessToken, refreshToken }));
        setShowPaymentModal(false)
        window.location.href = '/subscription';
        
    }

    return (
        <>
            <CommonPage>
                <div className="w-[1452px] mx-auto font-roboto text-center py-10">
                    <div className="mb-8">
                        <PageTitle
                            title="JobPrize 회원 서비스에 오신 것을 환영합니다"
                            description="당신의 취업을 응원합니다. AI와 컨설턴트가 함께하는 구독 서비스로 든든하게 준비해보세요."
                        />
                    </div>

                    <div className="bg-theme text-white rounded-lg p-8 mb-10 max-w-4xl mx-auto text-left shadow-lg">
                        <h2 className="text-3xl font-bold mb-2">{selectedPlan.name}</h2>
                        <p className="mb-6">모든 기능을 제한 없이 이용할 수 있는 프리미엄 서비스</p>
                        <div className="text-5xl font-bold mb-8">
                            월 {selectedPlan.price.toLocaleString()}원 <span className="text-2xl font-normal">(VAT 포함)</span>
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-x-8 gap-y-4 mb-8">
                            <div className="flex items-center space-x-3"><CheckIcon /><span>무제한 AI 첨삭 + 피드백</span></div>
                            <div className="flex items-center space-x-3"><CheckIcon /><span>무제한 컨설턴트 첨삭 + 피드백</span></div>
                        </div>

                        <div className="text-center">
                            <button
                                onClick={onClickSubscriptionButton}
                                className="bg-white text-theme font-bold py-3 px-8 rounded-lg text-lg hover:bg-gray-100 transition-colors"
                            >
                                구독 시작하기
                            </button>
                        </div>
                    </div>

                    <div className="py-10 mt-10">
                        <h2 className="text-3xl font-bold text-gray-800 mb-10">서비스 이용방법</h2>
                        <div className="grid md:grid-cols-3 gap-8 text-center">
                            <div className="flex flex-col items-center">
                                <div className="bg-semi_theme rounded-full p-4 mb-4 inline-block">
                                    <CreditCardIcon className="w-10 h-10 text-theme" />
                                </div>
                                <h3 className="text-xl font-semibold mb-2">구독 결제</h3>
                                <p className="text-gray-600">간편한 결제 시스템으로 구독을 시작하세요.<br /> 언제든지 해지 가능합니다.</p>
                            </div>
                            <div className="flex flex-col items-center">
                                <div className="bg-semi_theme rounded-full p-4 mb-4 inline-block">
                                    <DocumentTextIcon className="w-10 h-10 text-theme" />
                                </div>
                                <h3 className="text-xl font-semibold mb-2">이력서, 경력기술서 및 자기소개서 등록</h3>
                                <p className="text-gray-600">취업에 필요한 필수 서류를 완성하세요.</p>
                            </div>
                            <div className="flex flex-col items-center">
                                <div className="bg-semi_theme rounded-full p-4 mb-4 inline-block">
                                    <PeopleIcon className="w-10 h-10 text-theme" />
                                </div>
                                <h3 className="text-xl font-semibold mb-2">AI + 컨설턴트 컨설팅</h3>
                                <p className="text-gray-600">컨설팅에 최적화된 AI 및 전문 컨설턴트에게 컨설팅을 요청해보세요.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </CommonPage>

            {showPaymentModal && <PaymentModal
                paymentAmount={selectedPlan.price}
                onClick={onClickPaymentButton}
                onClose={setShowPaymentModal} />}
        </>
    )
};

export default SubscriptionPlansForMemberPage;