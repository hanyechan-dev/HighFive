package com.jobPrize.dto.company.company;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.enumerate.CompanyType;
import com.jobPrize.validation.businessNumber.BusinessNumber;
import com.jobPrize.validation.phone.Phone;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class CompanyCreateDto {

	
	@NotBlank(message = "기업명은 필수로 입력해야합니다.")
	@Size(max=20, message = "기업명은 20자 이하로 입력해야합니다.")
	private String companyName;

	@NotNull
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
	private CompanyType type;
	
	@Min(0)
	private Integer employeeCount;
	
	@NotNull(message = "설립일은 필수로 입력해야합니다")
	@Past
	private LocalDate establishedDate;
	
	private MultipartFile logoImageFile;
}