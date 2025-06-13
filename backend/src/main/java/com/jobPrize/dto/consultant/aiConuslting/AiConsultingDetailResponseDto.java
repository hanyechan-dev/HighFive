  package com.jobPrize.dto.consultant.aiConuslting;

import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingDetailResponseDto {

	private RequestResponseDto requestResponseDto;
	private AiConsultingResponseDto aiConsultingResponseDto;

	public static AiConsultingDetailResponseDto of(RequestResponseDto requestResponseDto, AiConsultingResponseDto aiConsultingResponseDto) {
		return AiConsultingDetailResponseDto.builder()
			.requestResponseDto(requestResponseDto)
			.aiConsultingResponseDto(aiConsultingResponseDto)
			.build();
	}
   
}