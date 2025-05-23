package com.jobPrize.dto.company.schedule;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {
	
	private Long id;
	
	private String title;

	private String content;

	private LocalDate date;

}