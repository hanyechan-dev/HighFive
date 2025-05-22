package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.SubscriptionRequestDto;
import com.jobPrize.admin01_service.dto.SubscriptionResponseDto;
import com.jobPrize.entity.common.UserType;

public interface SubscriptionService {
	
	void createSubscription(SubscriptionRequestDto subscriptionRequestDto) throws Exception;
	
	List<SubscriptionResponseDto> readSubscriberByUserTypeList(UserType userType) throws Exception;
	
	// 구독 기간 만료 시, User.isSubscribed 필드가 자동으로 false 설정되는 기능 구현 필수.
}
