package com.jobPrize.customException;

public class CustomOwnerMismatchException extends RuntimeException{
	public CustomOwnerMismatchException(String entityName, String action) {
		super("해당 "+ entityName +"의 "+ action +" 권한이 없습니다.");
	}

}
