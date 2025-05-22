package com.jobPrize.dto.consultant.consultantConsultingContent;

import com.jobPrize.entity.consultant.CommonEnum.DocumentType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantContentResponseDto {
	
	private Long id;
	private String item;
    private String content; 
    private DocumentType documentType;
}
