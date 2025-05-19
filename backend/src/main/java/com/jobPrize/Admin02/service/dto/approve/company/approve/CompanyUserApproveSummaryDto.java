package com.jobPrize.Admin02.service.dto.approve.company.approve;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyUserApproveSummaryDto {
	private String companyName;
	private String email;
	private String representativeName;
	private String phone;
	private String address;
	private LocalDate createdDate;
	
	public static CompanyUserApproveSummaryDto from(Company company) {
	User user = company.getUser();
	
	return CompanyUserApproveSummaryDto
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
