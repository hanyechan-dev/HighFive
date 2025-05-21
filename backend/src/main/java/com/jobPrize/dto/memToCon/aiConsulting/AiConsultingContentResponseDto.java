package com.jobPrize.dto.memToCon.aiConsulting;

import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.CommonEnum;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingContentResponseDto {

	private Long id;

	private CommonEnum.DocumentType type;
    
	private String item;
	
	private String content;

	public static AiConsultingContentResponseDto from(AiConsultingContent aiConsultingContent) {
		return AiConsultingContentResponseDto.builder()
			.id(aiConsultingContent.getId())
			.type(aiConsultingContent.getDocumentType())
			.item(aiConsultingContent.getItem())
			.content(aiConsultingContent.getContent())
			.build();
	}

}
