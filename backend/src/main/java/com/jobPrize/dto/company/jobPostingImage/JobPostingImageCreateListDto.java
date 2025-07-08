package com.jobPrize.dto.company.jobPostingImage;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobPostingImageCreateListDto {
	
	 @Valid
	 @NotNull(message = "이미지를 선택해주세요.")
	 private List<JobPostingImageCreateDto> jobPostingImageCreateDtos;
}
