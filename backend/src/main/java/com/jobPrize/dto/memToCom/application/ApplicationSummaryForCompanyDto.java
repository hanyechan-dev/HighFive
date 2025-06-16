package com.jobPrize.dto.memToCom.application;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.enumerate.EducationLevel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationSummaryForCompanyDto {
	
	private Long id;
	
	private String jobPostingTitle;

	private String name;
	
	private String genderType;
	
	private LocalDate birthDate;
	
	private boolean hasCareer;
	
	private String job;
	
	private String educationLevel;
	
	private LocalDate createdDate;
	
	private boolean isPassed;

	public static ApplicationSummaryForCompanyDto of(Application application, boolean hasCareer, EducationLevel educationLevel, boolean isPassed) {
		return ApplicationSummaryForCompanyDto.builder()
			.id(application.getId())
			.jobPostingTitle(application.getJobPosting().getTitle())
			.name(application.getMember().getUser().getName())
			.genderType(application.getMember().getUser().getGenderType().name())
			.birthDate(application.getMember().getUser().getBirthDate())
			.hasCareer(hasCareer)
			.job(application.getJobPosting().getJob())
			.educationLevel(educationLevel.name())
			.createdDate(application.getCreatedDate())
			.isPassed(isPassed)
			.build();
	}
}