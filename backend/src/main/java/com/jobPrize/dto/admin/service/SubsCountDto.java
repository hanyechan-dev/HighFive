package com.jobPrize.dto.admin.service;

import com.jobPrize.enumerate.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubsCountDto {
	private UserType userType;
	private long subs;
	private long unSubs;
}
