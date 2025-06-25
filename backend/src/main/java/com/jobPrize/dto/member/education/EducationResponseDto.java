package com.jobPrize.dto.member.education;


import java.time.LocalDate;

import com.jobPrize.entity.member.Education;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EducationResponseDto {
	
	private Long id;
	
	private String schoolName;
	
	private String educationLevel;
	
	private String major;
	
	private String gpa;
	
	private String location;
	
	private LocalDate enterDate;
	
	private LocalDate graduateDate;
	
	public static EducationResponseDto from(Education education) {
		return EducationResponseDto.builder()
				.id(education.getId())
				.schoolName(education.getSchoolName())
				.educationLevel(education.getEducationLevel().name())
				.major(education.getMajor())
				.gpa(education.getGpa())
				.location(education.getLocation())
				.enterDate(education.getEnterDate())
				.graduateDate(education.getGraduateDate())
				.build();
				
	}
}
