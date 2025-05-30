package com.jobPrize.dto.consultant.consultantConsulting;

import java.time.LocalDate;

import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.enumerate.ConsultingType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantConsultingSummaryDto {

	private Long consultantConsultingId;
	private String userName;
	private String targetJob;
	private String targetCompanyName;
	private LocalDate requestedDate;
	private ConsultingType consultingType;
	private boolean completed;
	
	public static ConsultantConsultingSummaryDto from(ConsultantConsulting consultantConsulting) {
		return ConsultantConsultingSummaryDto
    		.builder()
            .consultantConsultingId(consultantConsulting.getId())
            .userName(consultantConsulting.getAiConsulting().getRequest().getMember().getUser().getName())
            .targetJob(consultantConsulting.getAiConsulting().getRequest().getTargetJob())
            .targetCompanyName(consultantConsulting.getAiConsulting().getRequest().getTargetCompanyName())
            .requestedDate(consultantConsulting.getAiConsulting().getRequestedDate())
            .consultingType(consultantConsulting.getType())
            .completed(consultantConsulting.getCompletedDate() != null)	                
            .build();
	}
}
