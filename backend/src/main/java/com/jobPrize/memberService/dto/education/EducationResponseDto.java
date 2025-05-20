package com.jobPrize.memberService.dto.education;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.member.Education;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationResponseDto {
	
	private Long id;
	
	private String schoolName;
	
	private EducationLevel educationLevel;
	
	private String major;
	
	private BigDecimal gpa;
	
	private String location;
	
	private LocalDate enterDate;
	
	private LocalDate graduateDate;
	
	public static EducationResponseDto from(Education education) {
		return EducationResponseDto.builder()
				.id(education.getId())
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
