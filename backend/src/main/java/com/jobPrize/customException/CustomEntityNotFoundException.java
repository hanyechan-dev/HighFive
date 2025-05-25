package com.jobPrize.customException;

public class CustomEntityNotFoundException extends RuntimeException {

	public CustomEntityNotFoundException(String entityName) {
		super("존재하지 않는 "+entityName+"입니다.");
	}
}
