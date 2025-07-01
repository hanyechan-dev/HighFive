package com.jobPrize.dto.company.jobPostingImage;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobPostingImageCreateListDto {
	
	 @Valid
	 private List<JobPostingImageCreateDto> jobPostingImageCreateDtos;
}
