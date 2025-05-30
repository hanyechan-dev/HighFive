package com.jobPrize.enumerate;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
	일반회원, 기업회원, 컨설턴트회원, 관리자;
	
	@JsonCreator
	public static UserType from(String value) {
		return Arrays.stream(values()).filter(g -> g.name().equals(value)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 회원 값: " + value));
	}
}
