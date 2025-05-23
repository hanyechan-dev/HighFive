package com.jobPrize.dto.memToCom.application;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.EducationLevel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationSummaryForCompanyDto {
	
	private Long id;
	
	private String jobPostingTitle;

	private String name;
	
	private GenderType gender;
	
	private LocalDate birthDate;
	
	private boolean hasCareer;
	
	private String job;
	
	private EducationLevel educationLevel;
	
	private LocalDate createdDate;
	
	private boolean isInterested;

	public static ApplicationSummaryForCompanyDto of(Application application, boolean hasCareer, EducationLevel educationLevel, boolean isInterested) {
		return ApplicationSummaryForCompanyDto.builder()
			.id(application.getId())
			.jobPostingTitle(application.getJobPosting().getTitle())
			.name(application.getMember().getUser().getName())
			.gender(application.getMember().getUser().getGenderType())
			.birthDate(application.getMember().getUser().getBirthDate())
			.hasCareer(hasCareer)
			.job(application.getJobPosting().getJob())
			.educationLevel(educationLevel)
			.createdDate(application.getCreatedDate())
			.isInterested(isInterested)
			.build();
	}
}