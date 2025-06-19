package com.jobPrize.dto.admin.service;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountByDayDto {
	private LocalDate date;
	private Long signUps;
	private Long withdraws;
}
