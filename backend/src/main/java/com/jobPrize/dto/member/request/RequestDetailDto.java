package com.jobPrize.dto.member.request;

import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestDetailDto {
	private RequestResponseDto requestResponseDto;
	private AiConsultingResponseDto aiConsultingResponseDto;
	
	public static RequestDetailDto of(RequestResponseDto requestResponseDto, 
			AiConsultingResponseDto aiConsultingResponseDto) {
		
		return RequestDetailDto.builder()
			.requestResponseDto(requestResponseDto)
			.aiConsultingResponseDto(aiConsultingResponseDto)
			.build();
	}

}
