package com.jobPrize.dto.memToCon.request;

import java.util.List;

import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingContentResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestDetailDto {
	private RequestResponseDto requestResponseDto;
	private AiConsultingResponseDto aiConsultingResponseDto;
	private List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos;
	
	public static RequestDetailDto of(RequestResponseDto requestResponseDto, AiConsultingResponseDto aiConsultingResponseDto, List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos) {
		return RequestDetailDto.builder()
			.requestResponseDto(requestResponseDto)
			.aiConsultingResponseDto(aiConsultingResponseDto)
			.aiConsultingContentResponseDtos(aiConsultingContentResponseDtos)
			.build();
	}

}
