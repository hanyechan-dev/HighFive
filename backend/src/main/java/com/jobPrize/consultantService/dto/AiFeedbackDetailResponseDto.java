  package com.jobPrize.consultantService.dto;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.consultantService.dto.RequestDocument.CareerDescriptionResponseDto;
import com.jobPrize.consultantService.dto.RequestDocument.CoverLetterResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiFeedbackDetailResponseDto {

	private String targetCompanyName;
	private String targetJob;
	private LocalDate requestedDate;
	private LocalDate createdDate;

	private CareerDescriptionResponseDto careerDescription;
	private CoverLetterResponseDto coverLetter;

	private List<AiCommentResponseDto> aiComments;
   
}
