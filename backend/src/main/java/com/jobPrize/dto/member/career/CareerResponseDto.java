package com.jobPrize.dto.member.career;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jobPrize.entity.member.Career;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CareerResponseDto {
	
	private Long id;

	private String companyName;

	private String job;

	private String department;

	private String position;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	public static CareerResponseDto from(Career career) {
		return CareerResponseDto.builder()
				.id(career.getId())
				.companyName(career.getCompanyName())
				.job(career.getJob())
				.department(career.getDepartment())
				.position(career.getPosition())
				.startDate(career.getStartDate())
				.endDate(career.getEndDate())
				.build();
				
	}
	
	
}
