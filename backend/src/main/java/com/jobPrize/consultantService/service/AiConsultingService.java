package com.jobPrize.consultantService.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.jobPrize.consultantService.dto.AiConsultingSummaryDto;
import com.jobPrize.consultantService.dto.AiEditDetailResponseDto;
import com.jobPrize.consultantService.dto.AiFeedbackDetailResponseDto;

public interface AiConsultingService {

	Page<AiConsultingSummaryDto> getAllByCondition(Pageable pageable);

	AiEditDetailResponseDto getEditDetail(Long aiConsultingId);

	AiFeedbackDetailResponseDto getFeedbackDetail(Long aiConsultingId);
}
