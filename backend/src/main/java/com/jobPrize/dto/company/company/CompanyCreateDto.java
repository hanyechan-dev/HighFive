package com.jobPrize.dto.company.company;

import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.validation.businessNumber.BusinessNumber;
import com.jobPrize.validation.minForString.MinForString;
import com.jobPrize.validation.pastForString.PastForString;
import com.jobPrize.validation.phone.Phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CompanyCreateDto {

	
	@NotBlank(message = "기업명은 필수로 입력해야합니다.")
	@Size(max=20, message = "기업명은 20자 이하로 입력해야합니다.")
	private String companyName;

	@NotNull(message = "산업은 필수로 입력해야합니다.")
	private String industry;
	
	@NotBlank(message = "대표자명은 필수로 입력해야합니다")
	@Size(max=10, message = "대표자명은 10자 이하로 입력해야합니다.")
	private String representativeName;

	@NotBlank(message = "사업자 등록 번호는 필수로 입력해야합니다")
	@BusinessNumber
	private String businessNumber;

	@NotBlank(message = "기업 주소는 필수로 입력해야합니다")
	@Size(max=50, message = "기업 주소는 50자 이하로 입력해야합니다.")
	private String companyAddress;
	
	@NotBlank(message = "기업 전화번호는 필수로 입력해야합니다.")
	@Phone
	private String companyPhone;
	
	@Size(max=200, message = "기업 소개는 200자 이하로 입력해야합니다.")
	private String introduction;
	
	@NotNull
	private String companyType;
	
	@NotNull(message = "직원수는 필수로 입력해야합니다.")
	@MinForString(value = 0, message = "직원수는 0 이상을 입력하야합니다.")
	private String employeeCount;
	
	@NotNull(message = "설립일은 필수로 입력해야합니다")
	@PastForString(pattern = "yyyy-MM-dd", message = "설립일은 오늘 이전이어야 합니다.")
	private String establishedDate;
	
	@NotNull(message = "기업 로고는 필수로 입력해야합니다.")
	private MultipartFile logoImageFile;
}