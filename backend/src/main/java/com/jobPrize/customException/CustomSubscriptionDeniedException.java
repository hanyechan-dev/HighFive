package com.jobPrize.customException;

import com.jobPrize.enumerate.UserType;

public class CustomSubscriptionDeniedException extends RuntimeException{
	public CustomSubscriptionDeniedException(UserType allowedUserType, String action) {
		super("구독한" + allowedUserType.name() + "만 " + action + "할 수 있습니다.");
	}

}
