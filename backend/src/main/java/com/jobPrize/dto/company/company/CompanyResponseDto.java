package com.jobPrize.dto.company.company;

import java.time.LocalDate;

import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.company.Industry;
import com.jobPrize.validation.businessNumber.BusinessNumber;
import com.jobPrize.validation.phone.Phone;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
}