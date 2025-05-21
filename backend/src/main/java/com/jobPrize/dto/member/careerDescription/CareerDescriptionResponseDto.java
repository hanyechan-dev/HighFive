package com.jobPrize.dto.member.careerDescription;

import java.util.List;

import com.jobPrize.entity.member.CareerDescription;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CareerDescriptionResponseDto {
	
	private Long id;
	
	private String title;
	
	List<CareerDescriptionContentResponseDto> contents;
	
	public static CareerDescriptionResponseDto of(CareerDescription careerDescription,List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos) {
		return CareerDescriptionResponseDto.builder()
				.id(careerDescription.getId())
				.title(careerDescription.getTitle())
				.contents(careerDescriptionContentResponseDtos)
				.build();
	}
}
