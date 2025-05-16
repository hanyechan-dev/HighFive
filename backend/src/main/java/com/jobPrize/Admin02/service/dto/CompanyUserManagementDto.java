package com.jobPrize.Admin02.service.dto;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyUserManagementDto {

	private String companyName;
	private String representativeName;
	private String email;
	private String phone;
	private String address;
	private LocalDate createdDate;
	
	public static CompanyUserManagementDto from(Company company) {
		User user = company.getUser();
		
		return CompanyUserManagementDto
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
