package com.jobPrize.dto.consultant.aiConuslting;

import java.time.LocalDate;

import com.jobPrize.entity.consultant.AiConsulting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingSummaryDto {

	private Long aiConsultingId;
	private String nickname;
	private String targetJob;
	private String targetCompanyName;
	private LocalDate requestedDate;
	private String consultingType;

	public static AiConsultingSummaryDto from(AiConsulting aiConsulting) {
		return AiConsultingSummaryDto.builder()
			.aiConsultingId(aiConsulting.getId())
			.nickname(aiConsulting.getRequest().getMember().getNickname())
			.targetJob(aiConsulting.getRequest().getTargetJob())
			.targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
			.requestedDate(aiConsulting.getRequestedDate())
			.consultingType(aiConsulting.getConsultingType().name())
			.build();
	}
}
