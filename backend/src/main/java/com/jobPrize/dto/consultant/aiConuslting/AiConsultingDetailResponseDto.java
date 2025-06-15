  package com.jobPrize.dto.consultant.aiConuslting;

import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestForConsultantResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingDetailResponseDto {

	private RequestForConsultantResponseDto requestForConsultantResponseDto;
	private AiConsultingResponseDto aiConsultingResponseDto;

	public static AiConsultingDetailResponseDto of(RequestForConsultantResponseDto requestForConsultantResponseDto, AiConsultingResponseDto aiConsultingResponseDto) {
		return AiConsultingDetailResponseDto.builder()
			.requestForConsultantResponseDto(requestForConsultantResponseDto)
			.aiConsultingResponseDto(aiConsultingResponseDto)
			.build();
	}
   
}