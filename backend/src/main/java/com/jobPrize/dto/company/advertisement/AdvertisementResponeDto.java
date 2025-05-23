package com.jobPrize.dto.company.advertisement;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdvertisementResponeDto {
	private Long id;
	private String imageUrl;
	private LocalDate startDate;
	private LocalDate endDate;
}