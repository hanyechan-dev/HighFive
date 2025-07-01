package com.jobPrize.dto.member.careerDescription;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.member.CareerDescription;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CareerDescriptionResponseDto {
	
	private Long id;
	
	private String title;
	
	private LocalDate createdDate;
	
	List<CareerDescriptionContentResponseDto> contents;
	
	public static CareerDescriptionResponseDto of(CareerDescription careerDescription,List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos) {
		return CareerDescriptionResponseDto.builder()
				.id(careerDescription.getId())
				.title(careerDescription.getTitle())
				.createdDate(careerDescription.getCreatedDate())
				.contents(careerDescriptionContentResponseDtos)
				.build();
	}
}
