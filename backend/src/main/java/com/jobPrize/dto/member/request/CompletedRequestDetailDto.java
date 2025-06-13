package com.jobPrize.dto.member.request;

import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.consultantConsulting.ConsultantConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompletedRequestDetailDto {
	private RequestResponseDto requestResponseDto;
	private AiConsultingResponseDto aiConsultingResponseDto;
	private ConsultantConsultingResponseDto consultantConsultingResponseDto;

	public static CompletedRequestDetailDto of(RequestResponseDto requestResponseDto,
			AiConsultingResponseDto aiConsultingResponseDto,
			ConsultantConsultingResponseDto consultantConsultingResponseDto) {

		return CompletedRequestDetailDto.builder().requestResponseDto(requestResponseDto)
				.aiConsultingResponseDto(aiConsultingResponseDto)
				.consultantConsultingResponseDto(consultantConsultingResponseDto).build();
	}

}
