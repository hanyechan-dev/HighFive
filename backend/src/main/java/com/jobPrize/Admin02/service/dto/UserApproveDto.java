package com.jobPrize.Admin02.service.dto;

import com.jobPrize.entity.common.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserApproveDto {
	
	private Long id;
	private UserType memberType;
	private boolean approved;
	
}
