package com.jobPrize.dto.memToCon.aiConsulting;

import java.util.List;

import com.jobPrize.entity.consultant.AiConsulting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingResponseDto {

    private String consultingTypetype;
    
    private List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos;
    
    public static AiConsultingResponseDto of(AiConsulting aiConsulting, List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos) {
        return AiConsultingResponseDto.builder()
            .consultingTypetype(aiConsulting.getConsultingType().name())
            .aiConsultingContentResponseDtos(aiConsultingContentResponseDtos)
            .build();
    }

}
