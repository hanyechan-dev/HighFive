  package com.jobPrize.consultantService.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiFeedbackDetailResponseDto {

	private String targetCompanyName;
	private String targetJob;
	private LocalDate requestedDate;

	private String resume;
	private String careerDescription;
	private String coverLetter;

	private List<AiContentResponseDto> aiContents;
   
}