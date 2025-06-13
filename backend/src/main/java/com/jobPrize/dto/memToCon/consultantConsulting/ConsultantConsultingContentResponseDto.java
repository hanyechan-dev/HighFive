package com.jobPrize.dto.memToCon.consultantConsulting;

import com.jobPrize.entity.consultant.ConsultantConsultingContent;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class ConsultantConsultingContentResponseDto {
	
    private Long id;
    private String documentType;
    private String item;
    private String content;
    
    public static ConsultantConsultingContentResponseDto from(ConsultantConsultingContent consultantConsultingContent) {
    	return ConsultantConsultingContentResponseDto.builder()
    			.id(consultantConsultingContent.getId())
    			.documentType(consultantConsultingContent.getDocumentType().name())
    			.item(consultantConsultingContent.getItem())
    			.content(consultantConsultingContent.getContent())
    			.build();
    }

}
