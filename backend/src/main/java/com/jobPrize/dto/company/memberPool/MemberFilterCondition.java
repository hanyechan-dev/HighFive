package com.jobPrize.dto.company.memberPool;

import java.time.LocalDate;

import com.jobPrize.enumerate.EducationLevel;
import com.jobPrize.enumerate.GenderType;

import lombok.Getter;

@Getter
public class MemberFilterCondition {

	private boolean hasCareer;

	private EducationLevel educationLevel;

	private String address;
	
	private String job;
	
	private GenderType genderType;
	
	private LocalDate birthDate;
	
}