package com.jobPrize.dto.company.jobPosting;

import java.util.List;

import com.jobPrize.entity.company.CompanyType;
import com.jobPrize.entity.memToCom.EducationLevel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostingResponseDto {

	private Long id;

	private String title;

	private CompanyType companyType;

	private String workingHours;

	private String workLocation;

	private String job;

	private String careerType;

	private EducationLevel educationLevel;
	
	private int salary;
	
	private String content;
	
	private String requirement;
	
	private List<String> imageUrls;

}