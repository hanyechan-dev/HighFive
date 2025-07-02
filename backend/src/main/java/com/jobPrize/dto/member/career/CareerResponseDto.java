package com.jobPrize.dto.member.career;

import java.time.LocalDate;

import com.jobPrize.entity.member.Career;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
