package com.jobPrize.dto.memToCom.jobPosting;

import com.jobPrize.enumerate.EducationLevel;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class JobPostingFilterCondition {
	private String careerType;
	private EducationLevel educationLevel;
	private String workLocation;
	private String job;
	@Min(value=0,message = "급여는 0 이상이어야 합니다.")
	private int salary;
}
