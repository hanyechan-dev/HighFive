package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.UserType;

public interface SubscriptionService {
	
	// 실제 구독 기능을 넣어야 할 듯. 해당 부분 상의 필요.
	void insertSubscriber() throws Exception;
	
	List<Subscription> selectSubscriberByUserType(UserType userType);
}
