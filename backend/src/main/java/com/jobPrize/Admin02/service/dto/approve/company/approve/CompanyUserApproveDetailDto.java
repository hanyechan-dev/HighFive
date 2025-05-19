package com.jobPrize.Admin02.service.dto.approve.company.approve;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyUserApproveDetailDto {
	private String companyName;
	private String email;
	private String representativeName;
	private String phone;
	private String address;
	private boolean isSubscribed;
	private GenderType genderType;
	private LocalDate birthDate;
	private LocalDate createdDate;
	
	public static CompanyUserApproveDetailDto from(Company company) {
		User user = company.getUser();
		return CompanyUserApproveDetailDto
				.builder()
				.companyName(company.getCompanyName())
				.email(user.getEmail())
				.representativeName(company.getRepresentativeName())
				.phone(user.getPhone())
				.address(user.getAddress())
				.isSubscribed(user.isSubscribed())
				.genderType(user.getGenderType())
				.birthDate(user.getBirthDate())
				.createdDate(user.getCreatedDate())
				.build();
		
	}
	

}
