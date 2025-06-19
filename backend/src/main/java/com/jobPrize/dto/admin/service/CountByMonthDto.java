package com.jobPrize.dto.admin.service;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountByMonthDto {
	private int month;
	private Long signUps;
	private Long withdraws;
}
