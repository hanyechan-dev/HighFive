package com.jobPrize.memberService.dto.jobPositing;

import java.time.LocalDate;

import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingSummaryBySimilarityDto {
	
	private Long id;
	
	private String title;
	
	private String companyName;
	
	private CompanyType type;
	
	private String job;
	
	private String workLocation;
	
	private String careerType;
	
	private int similarityScore;
	
	private LocalDate createdDate;
	
	public static JobPostingSummaryBySimilarityDto from(Similarity similarity) {
		JobPosting jobPosting = similarity.getJobPosting();
		
		
		return JobPostingSummaryBySimilarityDto
				.builder()
				.id(jobPosting.getId())
				.title(jobPosting.getTitle())
				.companyName(jobPosting.getCompany().getCompanyName())
				.job(jobPosting.getJob())
				.type(jobPosting.getCompany().getType())
				.workLocation(jobPosting.getWorkLocation())
				.careerType(jobPosting.getCareerType())
				.similarityScore(similarity.getScore())
				.createdDate(jobPosting.getCreatedDate())
				.build();
		
	}
	
}
