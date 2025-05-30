package com.jobPrize.dto.company.jobPostingImage;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;


@Getter
public class JobPostingImageCreateListDto {
	
	 @Valid
	 private List<JobPostingImageCreateDto> jobPostingImageCreateDtos;
}
