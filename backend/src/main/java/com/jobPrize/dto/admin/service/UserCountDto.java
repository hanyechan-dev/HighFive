package com.jobPrize.dto.admin.service;

import com.jobPrize.enumerate.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCountDto {
	private UserType userType;
	private Long value;
}
