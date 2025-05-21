package com.jobPrize.service.consultant.consultantConsulting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentRequestDto;
import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentResponseDto;
import com.jobPrize.entity.common.UserType;

public interface ConsultantConsultingService {

	
	void approveConsulting(Long id, Long aiConsultingId,  UserType userType);

	void saveConsulting(Long id, Long consultantConsultingId, List<ConsultantContentRequestDto> dtoList);

	List<ConsultantContentResponseDto> readConsultantContentList(Long id,Long consultantConsultingId); // 응답 전용
	
	void completeConsulting(Long id,Long consultantConsultingId);

	Page<ConsultantConsultingSummaryDto> readConsultantConsultingPageByCondition(Long id, Pageable pageable);

	ConsultantEditDetailResponseDto readEditDetail(Long id,Long consultantConsultingId);

	ConsultantFeedBackDetailResponseDto readFeedbackDetail(Long id,Long consultantConsultingId);

}

