package com.jobPrize.memberService.dto.application;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Application;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationResponseDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private String job;
	
	private LocalDate createdDate;

	private boolean isPassed;
	
	private String resumeJson;
	
	private String coverLetterJson;
	
	private String careerDescriptionJson;

	public static ApplicationResponseDto from(Application application) {
		
		return ApplicationResponseDto.builder()
			.id(application.getId())
			.title(application.getJobPosting().getTitle())
			.companyName(application.getJobPosting().getCompany().getCompanyName())
			.job(application.getJobPosting().getJob())
			.createdDate(application.getCreatedDate())
			.isPassed(application.getPass() != null)
			.resumeJson(application.getResumeJson())
			.coverLetterJson(application.getCoverLetterJson())
			.careerDescriptionJson(application.getCareerDescriptionJson())
			.build();
	}
	
	
}
