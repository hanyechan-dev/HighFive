package com.jobPrize.dto.memToCon.aiConsulting;

import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.enumerate.ConsultingType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingResponseDto {

    private ConsultingType type;
    
    public static AiConsultingResponseDto from(AiConsulting aiConsulting) {
        return AiConsultingResponseDto.builder()
            .type(aiConsulting.getType())
            .build();
    }

}
