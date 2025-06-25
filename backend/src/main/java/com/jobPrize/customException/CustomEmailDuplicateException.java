package com.jobPrize.customException;

public class CustomEmailDuplicateException extends RuntimeException{
	public CustomEmailDuplicateException() {
		super("이미 사용중인 이메일입니다.");
	}

}
