package com.jobPrize.dto.memToCom.jobPosting;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.company.JobPosting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingForMemberResponseDto {

	private Long id;
	
	private String companyName;

	private String title;

	private String companyType;

	private String workingHours;

	private String workLocation;

	private String job;

	private String careerType;

	private String educationLevel;
	
	private int salary;
	
	private String content;
	
	private String requirement;
	
	private List<String> imageUrls;
	
	private LocalDate createdDate;
	
	private LocalDate expiredDate;

	public static JobPostingForMemberResponseDto of(JobPosting jobPosting, List<String> imageUrls) {
		return JobPostingForMemberResponseDto.builder()
			.id(jobPosting.getId())
			.companyName(jobPosting.getCompany().getCompanyName())
			.title(jobPosting.getTitle())
			.companyType(jobPosting.getCompany().getCompanyType().name())
			.workingHours(jobPosting.getWorkingHours())
			.workLocation(jobPosting.getWorkLocation())
			.job(jobPosting.getJob())
			.careerType(jobPosting.getCareerType())
			.educationLevel(jobPosting.getEducationLevel().name())
			.salary(jobPosting.getSalary())
			.content(jobPosting.getContent())
			.requirement(jobPosting.getRequirement())
			.imageUrls(imageUrls)
			.createdDate(jobPosting.getCreatedDate())
			.expiredDate(jobPosting.getExpiredDate())
			.build();
		
		
	}

}