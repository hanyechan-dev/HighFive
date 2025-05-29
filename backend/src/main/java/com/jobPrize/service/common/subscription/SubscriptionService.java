package com.jobPrize.service.common.subscription;

import java.util.List;

import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.entity.common.UserType;

public interface SubscriptionService {
	
	// 구독자 생성
	void createSubscription(Long id, UserType userType, PaymentRequestDto paymentRequestDto);
	
	// 사용자 유형에 따른 구독자 조회
	List<SubscriptionResponseDto> readSubscriberListByUserType(UserType userType, UserType targetUserType);
	
	// 구독 만료 시, 구독 상태 자동 업데이트
	void updateStatus();
	
	// 자기자신 구독 조회
	SubscriptionResponseDto readMySubscription(Long id);

}
