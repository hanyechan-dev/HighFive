  package com.jobPrize.dto.consultant.aiConuslting;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;

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