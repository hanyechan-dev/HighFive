package com.jobPrize.dummy;

import com.jobPrize.entity.memToCom.EducationLevel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostingFilterCondition {
	private String careerType;
	private EducationLevel educationLevel;
	private String workLocation;
	private String job;
	private int salary;
	

}
