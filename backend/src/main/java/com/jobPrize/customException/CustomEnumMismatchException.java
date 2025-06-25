package com.jobPrize.customException;

public class CustomEnumMismatchException extends RuntimeException{

	public CustomEnumMismatchException(String enumName) {
		super("존재하지 않는 " + enumName + "입니다.");
	}


	

}
