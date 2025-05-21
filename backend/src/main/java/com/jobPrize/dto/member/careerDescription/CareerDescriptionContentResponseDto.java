package com.jobPrize.dto.member.careerDescription;

import com.jobPrize.entity.member.CareerDescriptionContent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CareerDescriptionContentResponseDto {

	private Long id;
	
	private String item;

	private String content;
	
	public static CareerDescriptionContentResponseDto from(CareerDescriptionContent careerDescriptionContent) {
		return CareerDescriptionContentResponseDto.builder()
				.id(careerDescriptionContent.getId())
				.item(careerDescriptionContent.getItem())
				.content(careerDescriptionContent.getContent())
				.build();
	}

}
