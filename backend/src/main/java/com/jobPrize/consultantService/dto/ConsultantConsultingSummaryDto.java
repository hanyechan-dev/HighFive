package com.jobPrize.consultantService.dto;

import java.time.LocalDate;

import com.jobPrize.entity.consultant.CommonEnum.CompleteType;
import com.jobPrize.entity.consultant.CommonEnum.ConsultingType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantConsultingSummaryDto {

	private Long ConsultantConsultingId;
	private String userName;
	private String targetJob;
	private String targetCompanyName;
	private LocalDate requestedDate;
	private ConsultingType consultingType;
	private CompleteType completeType;

}
