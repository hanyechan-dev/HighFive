package com.jobPrize.dto.member.education;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EducationUpdateDto {
	
	@NotNull(message = "수정 시 id는 필수입니다.")
	private Long id;
	
	@Size(max = 20, message = "학교명은 20자 이하로 입력해야합니다.")
	@NotBlank(message = "학교명은 필수로 입력해야합니다")
	private String schoolName;
	
	@NotNull(message = "학위는 필수로 입력해야합니다")
	private String educationLevel;
	
	@Size(max = 20,message = "전공명은 20자 이하로 입력해야합니다.")
	private String major;
	
	@Pattern(
		    regexp = "^$|^\\s*$|^(?:[0-3](?:\\.\\d{1,2})?|4(?:\\.([0-4]|5{0,1}))?)$",
		    message = "학점은 0.0 이상 4.5 이하로 입력해야합니다."
		)
	private String gpa;
	
	@Size(max = 10,message = "지역은 10자 이하로 입력해야합니다.")
	@NotBlank(message = "지역은 필수로 입력해야합니다")
	private String location;
	
	@NotNull(message = "입학일은 필수로 입력해야합니다.")
	@Past(message = "입학일은 현재 이하로 입력해야합니다.")
	private LocalDate enterDate;
	
	@Past(message = "졸업일은 현재 이하로 입력해야합니다.")
	private LocalDate graduateDate;
}
