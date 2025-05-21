package com.jobPrize.dto.member.careerDescription;

import java.time.LocalDate;

import com.jobPrize.entity.member.CareerDescription;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CareerDescriptionSummaryDto {
	
	private Long id;
	
	private String title;
	
	private LocalDate createdDate;
	
	public static CareerDescriptionSummaryDto from(CareerDescription careerDescription) {
		return CareerDescriptionSummaryDto.builder()
				.id(careerDescription.getId())
				.title(careerDescription.getTitle())
				.createdDate(careerDescription.getCreatedDate())
				.build();
	}
}
