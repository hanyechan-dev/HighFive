package com.jobPrize.memberService.dto.jobPositing;

import java.time.LocalDate;

import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.company.JobPosting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingSummaryByConditionDto {
	
	private String title;
	
	private String companyName;
	
	private CompanyType type;
	
	private String job;
	
	private String workLocation;
	
	private String careerType;
	
	private LocalDate createdDate;
	
	public static JobPostingSummaryByConditionDto from(JobPosting jobPosting) {
		return JobPostingSummaryByConditionDto
				.builder()
				.title(jobPosting.getTitle())
				.companyName(jobPosting.getCompany().getCompanyName())
				.job(jobPosting.getJob())
				.type(jobPosting.getCompany().getType())
				.workLocation(jobPosting.getWorkLocation())
				.careerType(jobPosting.getCareerType())
				.createdDate(jobPosting.getCreatedDate())
				.build();
		
	}
	
}
