package com.jobPrize.customException;

import com.jobPrize.enumerate.UserType;

public class CustomApprovalDeniedException extends RuntimeException {
	public CustomApprovalDeniedException(UserType allowedUserType, String action) {
		super("승인된" + allowedUserType.name() + "만 " + action + "할 수 있습니다.");
	}

}
