package com.jobPrize.dto.admin.management.consultant;

import com.jobPrize.dto.admin.management.user.UserManagementSummaryDto;
import com.jobPrize.entity.consultant.Consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantManagementSummaryDto {
	
	private UserManagementSummaryDto userManagementSummaryDto;
	
	public static ConsultantManagementSummaryDto of(Consultant consultant, UserManagementSummaryDto userManagementSummaryDto) {
		return ConsultantManagementSummaryDto
				.builder()
				.userManagementSummaryDto(userManagementSummaryDto)
				.build();
	}

}
