package com.jobPrize.dto.company.schedule;

import java.time.LocalDate;

import com.jobPrize.entity.company.Schedule;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleSummaryDto {
	
	private Long id;
	
	private String title;

	private LocalDate date;

	public static ScheduleSummaryDto from(Schedule schedule) {
		return ScheduleSummaryDto.builder()
			.id(schedule.getId())
			.title(schedule.getTitle())
			.date(schedule.getDate())
			.build();
	}

}