package com.jobPrize.service.consultant.aiConsulting;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;

public interface AiConsultingService {

	Page<AiConsultingSummaryDto> readAiConsultingPageByCondition(Pageable pageable);

	AiEditDetailResponseDto readEditDetail(Long id, Long aiConsultingId);

	AiFeedbackDetailResponseDto readFeedbackDetail(Long id, Long aiConsultingId);
	
	void createConsulting(Long id, AiConsultingCreateDto aiConsultingCreateDto);

	
}
