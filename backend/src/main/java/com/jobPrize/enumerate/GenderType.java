package com.jobPrize.enumerate;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GenderType {
	남자, 여자;

	@JsonCreator
	public static GenderType from(String value) {
		return Arrays.stream(values()).filter(g -> g.name().equals(value)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 성별 값: " + value));
	}
}
