package com.jobPrize.dto.consultant.consultantConsulting;

import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.consultantConsulting.ConsultantConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantConsultingDetailResponseDto {

	private RequestResponseDto requestResponseDto;
	private AiConsultingResponseDto aiConsultingResponseDto;
	private ConsultantConsultingResponseDto consultantConsultingResponseDto;

	public static ConsultantConsultingDetailResponseDto of(RequestResponseDto requestResponseDto,
			AiConsultingResponseDto aiConsultingResponseDto,
			ConsultantConsultingResponseDto consultantConsultingResponseDto) {
		
	
		return ConsultantConsultingDetailResponseDto.builder()
				.requestResponseDto(requestResponseDto)
				.aiConsultingResponseDto(aiConsultingResponseDto)
				.consultantConsultingResponseDto(consultantConsultingResponseDto)
				.build();
	}
}
