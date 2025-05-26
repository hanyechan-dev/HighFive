package com.jobPrize.dto.consultant.consultantConsulting;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;
import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentResponseDto;
import com.jobPrize.entity.consultant.ConsultantConsulting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantEditDetailResponseDto {

	private String targetCompanyName;
	private String targetJob;
	private LocalDate requestedDate;
	private LocalDate createdDate;
	
	private String resume;
	private String careerDescription;
	private String coverLetter;

	private List<AiContentResponseDto> aiContents;
    private List<ConsultantContentResponseDto> consultantContents;

	public static ConsultantEditDetailResponseDto of(ConsultantConsulting consultantConsulting, List<AiContentResponseDto> aiContents, List<ConsultantContentResponseDto> consultantContents) {
		return ConsultantEditDetailResponseDto
				.builder()
				.targetCompanyName(consultantConsulting.getAiConsulting().getRequest().getTargetCompanyName())
				.targetJob(consultantConsulting.getAiConsulting().getRequest().getTargetJob())
				.requestedDate(consultantConsulting.getAiConsulting().getRequestedDate())
				.createdDate(consultantConsulting.getCreatedDate())
				.resume(consultantConsulting.getAiConsulting().getRequest().getResumeJson())
				.careerDescription(consultantConsulting.getAiConsulting().getRequest().getCareerDescriptionJson())
				.coverLetter(consultantConsulting.getAiConsulting().getRequest().getCoverLetterJson())
				.aiContents(aiContents)
				.consultantContents(consultantContents)
				.build();
	}
}
