package com.jobPrize.consultantService.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantCommentResponseDto {
	
	private String item;
    private String comment; 
}
