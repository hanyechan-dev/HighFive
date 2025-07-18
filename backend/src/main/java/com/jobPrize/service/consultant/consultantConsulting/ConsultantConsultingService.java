package com.jobPrize.service.consultant.consultantConsulting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public interface ConsultantConsultingService {

	
	void approveConsulting(Long id, UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId);

	void updateConsultantConsulting(Long id, ConsultantConsultingUpdateDto consultantConsultingUpdateDto);

	void completeConsulting(Long id, Long consultantConsultingId);

	Page<ConsultantConsultingSummaryDto> readConsultantConsultingPage(Long id, Pageable pageable);

	ConsultantConsultingDetailResponseDto readDetail(Long id, UserType userType, Long consultantConsultingId);

}

 