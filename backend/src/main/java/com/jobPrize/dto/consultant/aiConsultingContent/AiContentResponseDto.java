package com.jobPrize.dto.consultant.aiConsultingContent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiContentResponseDto {
	
	private String item;
    private String content;
}
