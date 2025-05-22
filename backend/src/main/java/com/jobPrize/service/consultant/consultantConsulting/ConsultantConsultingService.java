package com.jobPrize.service.consultant.consultantConsulting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.entity.common.UserType;

public interface ConsultantConsultingService {

	
	void approveConsulting(Long id, UserType userType, Long aiConsultingId);

	void updateConsultantConsulting(Long id, ConsultantConsultingUpdateDto consultantConsultingUpdateDto);

	void completeConsulting(Long id,Long consultantConsultingId);

	Page<ConsultantConsultingSummaryDto> readConsultantConsultingPageByCondition(Long id, Pageable pageable);

	ConsultantEditDetailResponseDto readEditDetail(Long id,Long consultantConsultingId);

	ConsultantFeedBackDetailResponseDto readFeedbackDetail(Long id, Long consultantConsultingId);

}

 