package com.jobPrize.dto.company.schedule;

import java.time.LocalDate;

import com.jobPrize.entity.company.Schedule;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {
	
	private Long id;
	
	private String title;

	private String content;

	private LocalDate date;

	public static ScheduleResponseDto from(Schedule schedule) {
		return ScheduleResponseDto.builder()
			.id(schedule.getId())
			.title(schedule.getTitle())
			.content(schedule.getContent())
			.date(schedule.getDate())
			.build();
	}

}