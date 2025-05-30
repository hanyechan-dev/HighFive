package com.jobPrize.customException;

import com.jobPrize.enumerate.UserType;

public class CustomAccessDeniedException extends RuntimeException{

	public CustomAccessDeniedException(UserType allowedUserType, String action) {
		super(allowedUserType.name()+"만 "+ action +" 할 수 있습니다.");
	}
	
	public CustomAccessDeniedException(UserType allowedUserType, UserType allowedUserType2, String action) {
		super(allowedUserType.name()+" 및 "+allowedUserType2.name()+"만 "+ action +" 할 수 있습니다.");
	}

}
