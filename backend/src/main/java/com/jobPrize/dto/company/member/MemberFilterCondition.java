package com.jobPrize.dto.company.member;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.memToCom.EducationLevel;

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