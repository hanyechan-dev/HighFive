package com.jobPrize.dto.admin.service;

import com.jobPrize.enumerate.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCountDto {
	private UserType userType;
	private long value;
}
