package com.jobPrize.dto.admin.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCountDto {
	private String userType;
	private long value;
}
