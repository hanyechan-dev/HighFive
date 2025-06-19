package com.jobPrize.dto.memToCom.application;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.memToCom.Application;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationResponseForCompanyDto {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String genderType;
	
	private LocalDate birthDate;
	
	private boolean hasCareer;
	
	private String job;
	
	private String phone;
	
	private LocalDate createdDate;

	private boolean isPassed;
	
	private String resumeJson;
	
	private String coverLetterJson;
	
	private String careerDescriptionJson;

public static ApplicationResponseForCompanyDto of(
		User user, 
		boolean hasCareer, 
		String job,
		Application application
		
		) {
	
	return ApplicationResponseForCompanyDto.builder()
		.id(application.getId())
		.name(user.getName())
		.email(user.getEmail())
		.genderType(user.getGenderType().name())
		.birthDate(user.getBirthDate())
		.hasCareer(hasCareer)
		.job(application.getJobPosting().getJob())
		.phone(user.getPhone())
		.createdDate(application.getCreatedDate())
		.isPassed(application.getPass() != null)
		.resumeJson(application.getResumeJson())
		.coverLetterJson(application.getCoverLetterJson())
		.careerDescriptionJson(application.getCareerDescriptionJson())		
		.build();
	}
}