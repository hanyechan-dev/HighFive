package com.jobPrize.dto.admin.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubsCountDto {
	private String userType;
	private long subs;
	private long unSubs;
}
