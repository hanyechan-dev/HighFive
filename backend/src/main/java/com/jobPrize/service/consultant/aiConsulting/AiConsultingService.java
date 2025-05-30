package com.jobPrize.service.consultant.aiConsulting;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public interface AiConsultingService {

	Page<AiConsultingSummaryDto> readAiConsultingPage(UserType userType, ApprovalStatus approvalStatus, Pageable pageable);

	AiEditDetailResponseDto readEditDetail(UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId);

	AiFeedbackDetailResponseDto readFeedbackDetail(UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId);
	
	void createAiConsulting(AiConsultingCreateDto aiConsultingCreateDto, Long requestId);
	
}
