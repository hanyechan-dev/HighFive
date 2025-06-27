package com.jobPrize.dto.admin.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountByMonthDto {
	private Integer year;
	private Integer month;
	private Long signUps;
	private Long withdraws;
}