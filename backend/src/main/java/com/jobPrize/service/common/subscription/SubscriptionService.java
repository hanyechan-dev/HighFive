package com.jobPrize.service.common.subscription;

import java.util.List;

import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.enumerate.UserType;

public interface SubscriptionService {
	
	// 구독자 생성
	TokenDto createSubscription(Long id, UserType userType, PaymentRequestDto paymentRequestDto);
	
	// 사용자 유형에 따른 구독자 정보 조회
	List<SubscriptionResponseDto> readSubscriberListByUserType(UserType userType, UserType targetUserType);
	
	// 사용자 유형에 따른 구독자 수 조회
	public List<SubscriptionResponseDto> ReadSubscribersCountByUserType(UserType userType, UserType targetUserType);
	
	// 구독 만료 시, 구독 상태 자동 업데이트
	void updateStatus();
	
	// 사용자가 자신의 마지막 구독 정보 조회
	SubscriptionResponseDto readSubscription(Long id);
	
	
	SubscriptionResponseDto scheduleUnsubscribe(Long id);

}
