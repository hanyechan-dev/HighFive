package com.jobPrize.dto.member.careerDescription;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CareerDescriptionCreateDto {
	
	@NotBlank(message = "제목은 필수로 입력해야합니다")
	@Size(max = 50, message = "제목은 50자 이하로 입력해야합니다.")
	private String title;
	
	@Valid
	List<CareerDescriptionContentCreateDto> contents;
}
