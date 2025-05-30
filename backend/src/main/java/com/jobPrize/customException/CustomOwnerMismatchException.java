package com.jobPrize.customException;

public class CustomOwnerMismatchException extends RuntimeException{
	public CustomOwnerMismatchException(String targetEntityName, String action) {
		super("해당 "+ targetEntityName +"의 "+ action +" 권한이 없습니다.");
	}

}
