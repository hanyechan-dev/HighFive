package com.jobPrize.memberService.dto.careerDescription;

import java.util.List;
import java.util.stream.Collectors;

import com.jobPrize.entity.member.CareerDescription;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CareerDescriptionResponseDto {
	
	private Long id;
	
	private String title;
	
	List<CareerDescriptionContentResponseDto> contents;
	
	public static CareerDescriptionResponseDto from(CareerDescription careerDescription) {
		return CareerDescriptionResponseDto.builder()
				.id(careerDescription.getId())
				.title(careerDescription.getTitle())
				.contents(
					careerDescription.getCareerDescriptionContents()
						.stream()
						.map(CareerDescriptionContentResponseDto::from)
						.collect(Collectors.toList())
				)
				.build();
	}
}
