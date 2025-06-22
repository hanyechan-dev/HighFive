package com.jobPrize.dto.admin.service;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsultingCountDto {
	private LocalDate date;
	private Long consultCount;
}
