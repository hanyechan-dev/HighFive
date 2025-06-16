package com.jobPrize.dto.memToCom.jobPosting;

import java.time.LocalDate;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingMainCardDto {
	
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
	
	private String imageUrl;
	
	public static JobPostingMainCardDto of(Similarity similarity, String imageUrl) {
		JobPosting jobPosting = similarity.getJobPosting();
		
		
		return JobPostingMainCardDto
				.builder()
				.id(jobPosting.getId())
				.title(jobPosting.getTitle())
				.companyName(jobPosting.getCompany().getCompanyName())
				.job(jobPosting.getJob())
				.companyType(jobPosting.getCompany().getType().name())
				.workLocation(jobPosting.getWorkLocation())
				.careerType(jobPosting.getCareerType())
				.educationLevel(jobPosting.getEducationLevel().name())
				.similarityScore(similarity.getScore())
				.createdDate(jobPosting.getCreatedDate())
				.imageUrl(imageUrl)
				.build();
		
	}
	
}
