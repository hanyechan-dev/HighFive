package com.jobPrize.consultantService.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiCommentResponseDto {
	
	private String item;
    private String comment; 
}
