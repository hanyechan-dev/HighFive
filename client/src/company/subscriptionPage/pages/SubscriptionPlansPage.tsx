import { useState } from "react";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import CreditCardIcon from "../components/CreditCardIcon";
import DocumentTextIcon from "../components/DocumentTextIcon";
import PeopleIcon from "../components/PeopleIcon";
import PaymentModal from "../../../features/subscription/modals/PaymentModal";

const CheckIcon = () => (
  <svg className="w-6 h-6 text-white flex-shrink-0" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
    <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd" />
  </svg>
);

const SubscriptionPlansPage = () => {
  // 결제 모달 상태 관리
  const [isPaymentModalOpen, setIsPaymentModalOpen] = useState(false);
  const selectedPlan = {
    name: "프리미엄 구독",
    price: 99000,
    period: "월"
  };

  const handleSubscribe = () => {
    // 결제 모달 열기
    setIsPaymentModalOpen(true);
  };

  const handlePaymentSuccess = () => {
    // 결제 성공 시 처리
    setIsPaymentModalOpen(false);
    // 구독 성공 후 구독 관리 페이지로 리다이렉트
    window.location.href = '/subscription';
  };

  const handlePaymentCancel = () => {
    // 결제 취소 시 처리
    setIsPaymentModalOpen(false);
  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto text-center py-10">
        <div className="mb-8">
          <PageTitle
            title="JobPrize 기업 서비스에 오신 것을 환영합니다"
            description="최적의 인재를 찾고 효율적인 채용 프로세스를 경험하기 위해 구독 서비스를 이용해보세요."
          />
        </div>

        <div className="bg-theme text-white rounded-lg p-8 mb-10 max-w-4xl mx-auto text-left shadow-lg">
          <h2 className="text-3xl font-bold mb-2">{selectedPlan.name}</h2>
          <p className="mb-6">모든 기능을 제한 없이 이용할 수 있는 프리미엄 서비스</p>
          <div className="text-5xl font-bold mb-8">
            월 {selectedPlan.price.toLocaleString()}원 <span className="text-2xl font-normal">(VAT 포함)</span>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 gap-x-8 gap-y-4 mb-8">
            <div className="flex items-center space-x-3"><CheckIcon /><span>무제한 채용공고 등록</span></div>
            <div className="flex items-center space-x-3"><CheckIcon /><span>지원자 AI 분석</span></div>
            <div className="flex items-center space-x-3"><CheckIcon /><span>AI 인재 추천 시스템</span></div>
            <div className="flex items-center space-x-3"><CheckIcon /><span>전용 채용 페이지</span></div>
            <div className="flex items-center space-x-3"><CheckIcon /><span>무제한 인재 제안</span></div>
            <div className="flex items-center space-x-3"><CheckIcon /><span>우선 기술지원</span></div>
          </div>

          <div className="text-center">
            <button 
              onClick={handleSubscribe}
              className="bg-white text-theme font-bold py-3 px-8 rounded-lg text-lg hover:bg-gray-100 transition-colors"
            >
              구독 시작하기
            </button>
          </div>
        </div>

        <div className="py-10 mt-10">
          <h2 className="text-3xl font-bold text-gray-800 mb-10">기업 서비스 이용방법</h2>
          <div className="grid md:grid-cols-3 gap-8 text-center">
            <div className="flex flex-col items-center">
              <div className="bg-semi_theme rounded-full p-4 mb-4 inline-block">
                <CreditCardIcon className="w-10 h-10 text-theme" />
              </div>
              <h3 className="text-xl font-semibold mb-2">구독 결제</h3>
              <p className="text-gray-600">간편한 결제 시스템으로 구독을 시작하세요. 언제든지 해지 가능합니다.</p>
            </div>
            <div className="flex flex-col items-center">
              <div className="bg-semi_theme rounded-full p-4 mb-4 inline-block">
                <DocumentTextIcon className="w-10 h-10 text-theme" />
              </div>
              <h3 className="text-xl font-semibold mb-2">기업 정보 등록</h3>
              <p className="text-gray-600">기업 프로필을 완성하고 채용 정보를 등록하세요.</p>
            </div>
            <div className="flex flex-col items-center">
              <div className="bg-semi_theme rounded-full p-4 mb-4 inline-block">
                <PeopleIcon className="w-10 h-10 text-theme" />
              </div>
              <h3 className="text-xl font-semibold mb-2">인재탐색 및 채용</h3>
              <p className="text-gray-600">AI 추천 인재를 확인하고 직접 채용 제안을 보내보세요.</p>
            </div>
          </div>
        </div>

        {/* 결제 모달 */}
        {isPaymentModalOpen && (
          <PaymentModal
            paymentAmount={selectedPlan.price}
            onClick={handlePaymentSuccess}
            onClose={handlePaymentCancel}
          />
        )}
      </div>
    </CommonPage>
  );
};

export default SubscriptionPlansPage; 