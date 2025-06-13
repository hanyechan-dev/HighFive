package com.jobPrize.service.consultant.aiConsultingContent;

import com.jobPrize.dto.member.aiConsulting.AiConsultingContentCreateDto;
import com.jobPrize.entity.consultant.AiConsulting;

public interface AiConsultingContentService {
	void createAiConsultingContent(AiConsulting aiConsulting, AiConsultingContentCreateDto aiConsultingContentCreateDto);
}
