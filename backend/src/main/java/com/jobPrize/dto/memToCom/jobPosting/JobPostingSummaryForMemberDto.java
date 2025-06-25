package com.jobPrize.dto.memToCom.jobPosting;

import java.time.LocalDate;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingSummaryForMemberDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private String companyType;
	
	private String job;
	
	private String workLocation;
	
	private String careerType;
	
	private String educationLevel;
	
	private int similarityScore;
	
	private LocalDate createdDate;
	
	public static JobPostingSummaryForMemberDto from(Similarity similarity) {
		JobPosting jobPosting = similarity.getJobPosting();
		
		
		return JobPostingSummaryForMemberDto
				.builder()
				.id(jobPosting.getId())
				.title(jobPosting.getTitle())
				.companyName(jobPosting.getCompany().getCompanyName())
				.job(jobPosting.getJob())
				.companyType(jobPosting.getCompany().getCompanyType().name())
				.workLocation(jobPosting.getWorkLocation())
				.careerType(jobPosting.getCareerType())
				.educationLevel(jobPosting.getEducationLevel().name())
				.similarityScore(similarity.getScore())
				.createdDate(jobPosting.getCreatedDate())
				.build();
		
	}
	
}
