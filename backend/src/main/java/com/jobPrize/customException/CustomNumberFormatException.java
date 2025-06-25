package com.jobPrize.customException;

public class CustomNumberFormatException extends RuntimeException{

	public CustomNumberFormatException(String fieldName) {
		super(fieldName+ " 는(은) 숫자 형식이어야 합니다.");
	}


	

}
