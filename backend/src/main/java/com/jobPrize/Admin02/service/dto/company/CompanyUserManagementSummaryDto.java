package com.jobPrize.Admin02.service.dto.company;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyUserManagementSummaryDto {

	private String companyName;
	private String email;
	private String representativeName;
	private String phone;
	private String address;
	private LocalDate createdDate;
	
	public static CompanyUserManagementSummaryDto from(Company company) {
		User user = company.getUser();
		
		return CompanyUserManagementSummaryDto
				.builder()
				.companyName(company.getCompanyName())
				.email(user.getEmail())
				.representativeName(company.getRepresentativeName())
				.phone(user.getPhone())
				.address(user.getAddress())
				.createdDate(user.getCreatedDate())
				.build();
	}
	
}
