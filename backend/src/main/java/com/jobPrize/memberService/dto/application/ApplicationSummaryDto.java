package com.jobPrize.memberService.dto.application;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Application;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationSummaryDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private String job;
	
	private boolean isPassed;
	
	private LocalDate createdDate;
	
	

	public static ApplicationSummaryDto from(Application application) {
		return ApplicationSummaryDto.builder()
			.id(application.getId())
			.title(application.getJobPosting().getTitle())
			.companyName(application.getJobPosting().getCompany().getCompanyName())
			.job(application.getJobPosting().getJob())
			.isPassed(application.getPass()!=null)
			.createdDate(application.getCreatedDate())
			.build();
	}
	
	
}
