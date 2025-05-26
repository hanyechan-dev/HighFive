  package com.jobPrize.dto.consultant.aiConuslting;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;
import com.jobPrize.entity.consultant.AiConsulting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiEditDetailResponseDto {

	private String targetCompanyName;
	private String targetJob;
	private LocalDate requestedDate;
	
	private String resume;
	private String careerDescription;
	private String coverLetter;

	private List<AiContentResponseDto> aiContents;

	public static AiEditDetailResponseDto of(AiConsulting aiConsulting,List<AiContentResponseDto> aiContents) {
		return AiEditDetailResponseDto.builder()
			.targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
			.targetJob(aiConsulting.getRequest().getTargetJob())
			.requestedDate(aiConsulting.getRequestedDate())
			.resume(aiConsulting.getRequest().getResumeJson())
			.careerDescription(aiConsulting.getRequest().getCareerDescriptionJson())
			.coverLetter(aiConsulting.getRequest().getCoverLetterJson())
			.aiContents(aiContents)
			.build();
	}
   
}

