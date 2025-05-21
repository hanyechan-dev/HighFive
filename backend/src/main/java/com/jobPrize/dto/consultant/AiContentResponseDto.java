package com.jobPrize.dto.consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiContentResponseDto {
	
	private String item;
    private String content; 
}
