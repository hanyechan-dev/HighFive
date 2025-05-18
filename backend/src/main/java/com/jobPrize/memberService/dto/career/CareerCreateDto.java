package com.jobPrize.memberService.dto.career;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CareerCreateDto {

	@Size(max = 20, message = "회사명은 20자 이하로 입력해야합니다.")
	@NotBlank(message = "회사명은 필수로 입력해야합니다")
	private String companyName;

	@Size(max = 20, message = "직무는 20자 이하로 입력해야합니다.")
	@NotBlank(message = "직무는 필수로 입력해야합니다")
	private String job;
	
	@Size(max = 10, message = "부서는 10자 이하로 입력해야합니다.")
	@NotBlank(message = "부서는 필수로 입력해야합니다")
	private String department;
	
	@Size(max = 10, message = "직급은 10자 이하로 입력해야합니다.")
	@NotBlank(message = "직급은 필수로 입력해야합니다")
	private String position;
	
	@NotNull(message = "입사일은 필수로 입력해야합니다.")
	@Past(message = "입사일은 현재 이하로 입력해야합니다.")
	private LocalDate startDate;
	
	@Past(message = "퇴사일은 현재 이하로 입력해야합니다.")
	private LocalDate endDate;
	
	
}
