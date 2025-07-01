package com.jobPrize.dto.company.company;

import java.time.LocalDate;

import com.jobPrize.entity.company.Company;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CompanyResponseDto {

	private String companyName;

	private String industry;

	private String representativeName;

	private String businessNumber;

	private String companyAddress;

	private String companyPhone;

	private String introduction;

	private String companyType;

	private Integer employeeCount;

	private LocalDate establishedDate;
	
	private String imageUrl;

	public static CompanyResponseDto of(Company company, String imageUrl) {
		return CompanyResponseDto.builder()
			.companyName(company.getCompanyName())
			.industry(company.getIndustry())
			.representativeName(company.getRepresentativeName())
			.businessNumber(company.getBusinessNumber())
			.companyAddress(company.getCompanyAddress())
			.companyPhone(company.getCompanyPhone())
			.introduction(company.getIntroduction())
			.companyType(company.getCompanyType().name())
			.employeeCount(company.getEmployeeCount())
			.establishedDate(company.getEstablishedDate())
			.imageUrl(imageUrl)
			.build();
	}
}