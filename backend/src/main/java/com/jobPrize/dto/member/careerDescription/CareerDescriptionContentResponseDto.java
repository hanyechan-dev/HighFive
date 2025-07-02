package com.jobPrize.dto.member.careerDescription;

import com.jobPrize.entity.member.CareerDescriptionContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
