package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.SubscriptionRequestDto;
import com.jobPrize.admin01_service.dto.SubscriptionResponseDto;
import com.jobPrize.entity.common.UserType;

public interface SubscriptionService {
	
	// 구독자 생성
	void createSubscription(SubscriptionRequestDto subscriptionRequestDto) throws Exception;
	
	// 사용자 유형에 따른 구독자 조회
	List<SubscriptionResponseDto> readSubscriberByUserTypeList(UserType userType) throws Exception;
	
	// 구독 만료 시, 구독 상태 자동 업데이트
	void updateStatus();

}
