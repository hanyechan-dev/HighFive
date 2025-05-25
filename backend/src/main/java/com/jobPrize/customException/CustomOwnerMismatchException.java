package com.jobPrize.customException;

public class CustomOwnerMismatchException extends RuntimeException{
	public CustomOwnerMismatchException(String target, String action) {
		super("해당 "+ target +"의 "+ action +" 권한이 없습니다.");
	}

}
