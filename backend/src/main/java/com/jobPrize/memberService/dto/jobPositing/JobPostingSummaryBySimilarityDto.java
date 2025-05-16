package com.jobPrize.memberService.dto.jobPositing;

import java.time.LocalDate;

import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.memToCom.Similarity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingSummaryBySimilarityDto {
	
	private String title;
	
	private String companyName;
	
	private CompanyType type;
	
	private String job;
	
	private String workLocation;
	
	private String careerType;
	
	private int similarityScore;
	
	private LocalDate createdDate;
	
	public static JobPostingSummaryBySimilarityDto from(Similarity similarity) {
		return JobPostingSummaryBySimilarityDto
				.builder()
				.title(similarity.getJobPosting().getTitle())
				.companyName(similarity.getJobPosting().getCompany().getCompanyName())
				.job(similarity.getJobPosting().getJob())
				.type(similarity.getJobPosting().getCompany().getType())
				.workLocation(similarity.getJobPosting().getWorkLocation())
				.careerType(similarity.getJobPosting().getCareerType())
				.similarityScore(similarity.getScore())
				.createdDate(similarity.getJobPosting().getCreatedDate())
				.build();
		
	}
	
}
