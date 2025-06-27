import { useEffect, useState } from "react";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { SubscriptionResponse } from "../props/SubscriptionProps";
import { scheduleUnsubscribeApi, SubscriptionApi } from "../../../common/apis/SubscriptionApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../../common/components/title/PageTitle";


const CheckIcon = () => (
    <svg className="w-6 h-6 text-white flex-shrink-0" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
        <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd" />
    </svg>
);

const disabledBgColer = 'bg-white/70';
const defaultBgColer = 'bg-white hover:bg-gray-100 cursor-pointer';

const SubscriptionManagementPage = () => {
    const [subscription, setSubscription] = useState<SubscriptionResponse>();
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        fetchSubscription();
    }, []);

    const fetchSubscription = async () => {
        setIsLoading(true);
        try {
            const res = await SubscriptionApi();
            setSubscription(res.data);
        } catch (err) {
            printErrorInfo(err);
        } finally {
            setIsLoading(false);
        }
    };

    const formatDate = (dateString: string) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        return `${year}.${month}.${day}`;
    };

    const handleCancelSubscription = async () => {
        if (window.confirm('정말 구독을 취소하시겠습니까?')) {
            try {
                const res = await scheduleUnsubscribeApi();
                console.log('구독 취소됨');
                alert('구독이 취소되었습니다.');
                setSubscription(res.data);
                
            }
            catch(err) {
                printErrorInfo(err);
            }

        }
    };

    if (isLoading) {
        return (
            <CommonPage>
                <div className="w-[1452px] mx-auto font-roboto text-center py-20">
                    <p className="text-gray-500">구독 정보를 불러오는 중입니다...</p>
                </div>
            </CommonPage>
        );
    }

    if (!subscription) {
        return (
            <CommonPage>
                <div className="w-[1452px] mx-auto font-roboto text-center py-12">
                    <p className="text-gray-500">구독 정보가 없습니다.</p>
                </div>
            </CommonPage>
        );
    }

    return (
        <CommonPage>
            <div className="w-[1452px] mx-auto font-roboto text-center py-10">
                <div className="mb-8">
                    <PageTitle
                        title="구독 관리"
                        description="현재 구독 정보를 확인하고 관리하세요"
                    />
                </div>

                <div className="inline-block border-2 border-green-500 rounded-lg p-4 mb-10 bg-green-50">
                    <div className="flex items-center space-x-4">
                        <span className="text-xl font-bold text-green-600">구독 이용중 입니다</span>
                        <div className="border-l border-gray-300 h-6"></div>
                        <span className="text-lg text-gray-700">
                            구독 기간: {formatDate(subscription.startDate)} ~ {formatDate(subscription.endDate)}
                        </span>
                    </div>
                </div>


                <div className="bg-theme text-white rounded-lg p-8 mb-10 max-w-4xl mx-auto text-left shadow-lg">
                    <p className="mb-6">모든 기능을 제한 없이 이용할 수 있는 프리미엄 서비스</p>
                    <div className="text-5xl font-bold mb-8">
                        월 99,000원 <span className="text-2xl font-normal">(VAT 포함)</span>
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
                        <button className={`${subscription.unsubscribeScheduled ? disabledBgColer : defaultBgColer} text-theme font-bold py-3 px-8 rounded-lg text-lg  transition-colors mr-4`} onClick={handleCancelSubscription} disabled={subscription.unsubscribeScheduled}>
                            {subscription.unsubscribeScheduled ? "구독 해지 예정":"구독 해지하기"}
                        </button>
                    </div>
                </div>
            </div>
        </CommonPage>
    );
};

export default SubscriptionManagementPage; 