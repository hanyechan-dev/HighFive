package com.jobPrize.dto.admin.management.company;

import com.jobPrize.dto.admin.management.user.UserManagementSummaryDto;
import com.jobPrize.entity.company.Company;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyManagementSummaryDto {

	private String companyName;

	private UserManagementSummaryDto userManagementSummaryDto;
	
	public static CompanyManagementSummaryDto of(Company company, UserManagementSummaryDto userManagementSummaryDto) {
		
		return CompanyManagementSummaryDto
				.builder()
				.companyName(company.getCompanyName())
				.userManagementSummaryDto(userManagementSummaryDto)
				.build();
	}
	
}
