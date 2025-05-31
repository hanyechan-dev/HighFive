package com.jobPrize.customException;

import com.jobPrize.enumerate.UserType;

public class CustomSubscriptionDeniedException extends RuntimeException{
	public CustomSubscriptionDeniedException(UserType allowedUserType, String entityName, String action) {
		super("구독한" + allowedUserType.name() + "만 " + entityName + "을 " + action + "할 수 있습니다.");
	}

}
