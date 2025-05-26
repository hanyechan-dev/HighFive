package com.jobPrize.dto.consultant.aiConuslting;

import java.time.LocalDate;

import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.CommonEnum.ConsultingType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingSummaryDto {

	private Long aiConsultingId;
	private String userName;
	private String targetJob;
	private String targetCompanyName;
	private LocalDate requestedDate;
	private ConsultingType consultingType;

	public static AiConsultingSummaryDto from(AiConsulting aiConsulting) {
		return AiConsultingSummaryDto.builder()
			.aiConsultingId(aiConsulting.getId())
			.userName(aiConsulting.getRequest().getMember().getUser().getName())
			.targetJob(aiConsulting.getRequest().getTargetJob())
			.targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
			.requestedDate(aiConsulting.getRequestedDate())
			.consultingType(aiConsulting.getType())
			.build();
	}
}
