package com.jobPrize.memberService.dto.education;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.member.Education;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EducationResponseDto {
	
	private String schoolName;
	
	private EducationLevel educationLevel;
	
	private String major;
	
	private BigDecimal gpa;
	
	private String location;
	
	private LocalDate enterDate;
	
	private LocalDate graduateDate;
	
	public static EducationResponseDto from(Education education) {
		return EducationResponseDto.builder()
				.schoolName(education.getSchoolName())
				.educationLevel(education.getEducationLevel())
				.major(education.getMajor())
				.gpa(education.getGpa())
				.location(education.getLocation())
				.enterDate(education.getEnterDate())
				.graduateDate(education.getGraduateDate())
				.build();
				
	}
}
