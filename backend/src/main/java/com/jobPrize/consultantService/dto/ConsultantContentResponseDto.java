package com.jobPrize.consultantService.dto;

import com.jobPrize.entity.consultant.CommonEnum.DocumentType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantContentResponseDto {
	
	private String item;
    private String content; 
    private DocumentType documentType;
}
