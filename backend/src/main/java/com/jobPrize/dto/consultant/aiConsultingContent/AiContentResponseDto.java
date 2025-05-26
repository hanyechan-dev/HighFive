package com.jobPrize.dto.consultant.aiConsultingContent;

import com.jobPrize.entity.consultant.AiConsultingContent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiContentResponseDto {
	
	private String item;
    private String content;

    public static AiContentResponseDto from(AiConsultingContent aiConsultingContent) {
		return AiContentResponseDto.builder()
			.item(aiConsultingContent.getItem())
			.content(aiConsultingContent.getContent())
			.build();
	}
}
