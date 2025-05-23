package com.jobPrize.dto.memToCom.application;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Application;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationSummaryForMemberDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private String job;
	
	private boolean isPassed;
	
	private LocalDate createdDate;
	
	

	public static ApplicationSummaryForMemberDto from(Application application) {
		return ApplicationSummaryForMemberDto.builder()
			.id(application.getId())
			.title(application.getJobPosting().getTitle())
			.companyName(application.getJobPosting().getCompany().getCompanyName())
			.job(application.getJobPosting().getJob())
			.isPassed(application.getPass()!=null)
			.createdDate(application.getCreatedDate())
			.build();
	}
	
	
}
