package com.jobPrize.consultantService.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantEditDetailResponseDto {

	private String targetCompanyName;
	private String targetJob;
	private LocalDate requestedDate;
	private LocalDate createdDate;

	private String careerDescription;
	private String coverLetter;

	private List<AiCommentResponseDto> aiComments;
    private List<ConsultantCommentResponseDto> consultantComments;
}
