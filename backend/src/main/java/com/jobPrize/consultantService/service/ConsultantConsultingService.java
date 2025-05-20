package com.jobPrize.consultantService.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.consultantService.dto.ConsultantContentRequestDto;
import com.jobPrize.consultantService.dto.ConsultantContentResponseDto;
import com.jobPrize.consultantService.dto.ConsultantConsultingSummaryDto;
import com.jobPrize.consultantService.dto.ConsultantEditDetailResponseDto;
import com.jobPrize.consultantService.dto.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.entity.common.UserType;

public interface ConsultantConsultingService {

	
	void approveConsulting(Long consultantId, Long aiConsultingId,  UserType userType);

	void saveConsultingContents(Long consultantId, Long consultantConsultingId, List<ConsultantContentRequestDto> dtoList);

	List<ConsultantContentResponseDto> getConsultingContentResponses(Long consultantId,Long consultantConsultingId); // 응답 전용
	
	void completeConsulting(Long consultantId,Long consultantConsultingId);

	Page<ConsultantConsultingSummaryDto> getAllByCondition(Long consultantId, Pageable pageable);

	ConsultantEditDetailResponseDto getEditDetail(Long consultantId,Long consultantConsultingId);

	ConsultantFeedBackDetailResponseDto getFeedbackDetail(Long consultantId,Long consultantConsultingId);

}

