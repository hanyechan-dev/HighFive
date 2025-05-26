package com.jobPrize.service.consultant.aiConsulting;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.entity.common.UserType;

public interface AiConsultingService {

	Page<AiConsultingSummaryDto> readAiConsultingPage(UserType userType, Pageable pageable);

	AiEditDetailResponseDto readEditDetail(Long id, UserType userType, Long aiConsultingId);

	AiFeedbackDetailResponseDto readFeedbackDetail(Long id, UserType userType, Long aiConsultingId);
	
	void createAiConsulting(AiConsultingCreateDto aiConsultingCreateDto, Long requestId);
	
}
