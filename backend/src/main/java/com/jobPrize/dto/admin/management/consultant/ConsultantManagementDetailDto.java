package com.jobPrize.dto.admin.management.consultant;

import com.jobPrize.dto.admin.management.user.UserManagementDetailDto;
import com.jobPrize.entity.consultant.Consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantManagementDetailDto {

	private UserManagementDetailDto userManagementDetailDto;
	
	public static ConsultantManagementDetailDto of(Consultant consultant, UserManagementDetailDto userManagementDetailDto) {
		return ConsultantManagementDetailDto
				.builder()
				.userManagementDetailDto(userManagementDetailDto)
				.build();
	}
	

}
