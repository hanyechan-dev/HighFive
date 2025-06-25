package com.jobPrize.customException;

public class CustomDateFormatException extends RuntimeException{

	public CustomDateFormatException(String fieldName) {
		super(fieldName+ " 형식이 잘못되었습니다. yyyy-MM-dd 형식이어야 합니다.");
	}

}
