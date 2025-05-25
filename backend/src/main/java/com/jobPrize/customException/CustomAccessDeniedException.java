package com.jobPrize.customException;

import com.jobPrize.entity.common.UserType;

public class CustomAccessDeniedException extends RuntimeException{

	public CustomAccessDeniedException(UserType userType, String action) {
		super(userType.name()+"만 "+ action +" 할 수 있습니다.");
	}
	
	public CustomAccessDeniedException(UserType userType, UserType userType2, String action) {
		super(userType.name()+" 및 "+userType2.name()+"만 "+ action +" 할 수 있습니다.");
	}

}
