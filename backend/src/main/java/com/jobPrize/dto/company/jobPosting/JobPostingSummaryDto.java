package com.jobPrize.dto.company.jobPosting;

import java.time.LocalDate;

import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.EducationLevel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingSummaryDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private CompanyType type;
	
	private String job;
	
	private String workLocation;
	
	private String careerType;
	
	private EducationLevel educationLevel;
	
	private LocalDate createdDate;
	
	public static JobPostingSummaryDto from(JobPosting jobPosting) {
		return JobPostingSummaryDto
				.builder()
				.id(jobPosting.getId())
				.title(jobPosting.getTitle())
				.companyName(jobPosting.getCompany().getCompanyName())
				.job(jobPosting.getJob())
				.type(jobPosting.getCompany().getType())
				.workLocation(jobPosting.getWorkLocation())
				.careerType(jobPosting.getCareerType())
				.educationLevel(jobPosting.getEducationLevel())
				.createdDate(jobPosting.getCreatedDate())
				.build();
		
	}
	
}
