package com.jobPrize.dto.company.memberPool;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MemberFilterCondition {

	private boolean hasCareer;

	private String educationLevel;

	private String address;
	
	private String job;
	
	private String genderType;
	
	private LocalDate birthDate;
	
}