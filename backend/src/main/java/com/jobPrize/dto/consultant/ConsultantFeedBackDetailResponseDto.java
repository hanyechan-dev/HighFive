package com.jobPrize.dto.consultant;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantFeedBackDetailResponseDto {

	private String targetCompanyName;
	private String targetJob;
	private LocalDate requestedDate;
	private LocalDate createdDate;
	
	private String resume;
	private String careerDescription;
	private String coverLetter;

	private List<AiContentResponseDto> aiContents;
    private List<ConsultantContentResponseDto> consultantContents;
}
