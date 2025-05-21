package com.jobPrize.dto.consultant;

import java.time.LocalDate;

import com.jobPrize.entity.consultant.CommonEnum.ConsultingType;

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

}
