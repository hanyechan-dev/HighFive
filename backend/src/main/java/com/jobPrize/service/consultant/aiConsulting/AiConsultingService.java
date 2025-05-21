package com.jobPrize.service.consultant.aiConsulting;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.AiFeedbackDetailResponseDto;

public interface AiConsultingService {

	Page<AiConsultingSummaryDto> readAiConsultingPageByCondition(Pageable pageable);

	AiEditDetailResponseDto readEditDetail(Long id, Long aiConsultingId);

	AiFeedbackDetailResponseDto readFeedbackDetail(Long id, Long aiConsultingId);

	
}
