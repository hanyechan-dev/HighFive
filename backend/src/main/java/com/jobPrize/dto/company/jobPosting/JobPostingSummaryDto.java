package com.jobPrize.dto.company.jobPosting;

import java.time.LocalDate;

import com.jobPrize.entity.company.JobPosting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingSummaryDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private String companyType;
	
	private String job;
	
	private String workLocation;
	
	private String careerType;
	
	private String educationLevel;
	
	private LocalDate createdDate;
	
	public static JobPostingSummaryDto from(JobPosting jobPosting) {
		return JobPostingSummaryDto
				.builder()
				.id(jobPosting.getId())
				.title(jobPosting.getTitle())
				.companyName(jobPosting.getCompany().getCompanyName())
				.job(jobPosting.getJob())
				.companyType(jobPosting.getCompany().getCompanyType().name())
				.workLocation(jobPosting.getWorkLocation())
				.careerType(jobPosting.getCareerType())
				.educationLevel(jobPosting.getEducationLevel().name())
				.createdDate(jobPosting.getCreatedDate())
				.build();
		
	}
	
}
