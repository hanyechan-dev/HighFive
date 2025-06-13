package com.jobPrize.dto.memToCon.aiConsulting;

import com.jobPrize.entity.consultant.AiConsultingContent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingContentResponseDto {

	private Long id;

	private String documentType;
    
	private String item;
	
	private String content;

	public static AiConsultingContentResponseDto from(AiConsultingContent aiConsultingContent) {
		return AiConsultingContentResponseDto.builder()
			.id(aiConsultingContent.getId())
			.documentType(aiConsultingContent.getDocumentType().name())
			.item(aiConsultingContent.getItem())
			.content(aiConsultingContent.getContent())
			.build();
	}

}
