package com.jobPrize.dto.admin.management.company;

import java.time.LocalDate;

import com.jobPrize.dto.admin.management.user.UserManagementDetailDto;
import com.jobPrize.entity.company.Company;
import com.jobPrize.enumerate.CompanyType;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CompanyManagementDetailDto {
	
	private UserManagementDetailDto userManagementDetailDto;

	private String industry;

	private String companyName;

	private String representativeName;

	private String businessNumber;

	private String companyAddress;

	private String companyPhone;

	private String introduction;

	private CompanyType type;

	private int employeeCount;

	private LocalDate establishedDate;

	
	public static CompanyManagementDetailDto of(Company company, UserManagementDetailDto userManagementDetailDto) {
		return CompanyManagementDetailDto.builder()
			.userManagementDetailDto(userManagementDetailDto)
			.industry(company.getIndustry())
			.companyName(company.getCompanyName())
			.representativeName(company.getRepresentativeName())
			.businessNumber(company.getBusinessNumber())
			.companyAddress(company.getCompanyAddress())
			.companyPhone(company.getCompanyPhone())
			.introduction(company.getIntroduction())
			.type(company.getType())
			.employeeCount(company.getEmployeeCount())
			.establishedDate(company.getEstablishedDate())
			.build();
	}

}
