package com.jobPrize.dto.company.company;

import java.time.LocalDate;

import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.company.Industry;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CompanyResponseDto {

	private String companyName;

	private Industry industry;

	private String representativeName;

	private String businessNumber;

	private String companyAddress;

	private String companyPhone;

	private String introduction;

	private CompanyType type;

	private Integer employeeCount;

	private LocalDate establishedDate;

	public static CompanyResponseDto from(Company company) {
		return CompanyResponseDto.builder()
			.companyName(company.getCompanyName())
			.industry(company.getIndustry())
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