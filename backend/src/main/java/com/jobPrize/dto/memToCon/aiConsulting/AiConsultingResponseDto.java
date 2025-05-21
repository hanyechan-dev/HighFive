package com.jobPrize.dto.memToCon.aiConsulting;

import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.CommonEnum;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiConsultingResponseDto {

    private CommonEnum.ConsultingType type;
    
    public static AiConsultingResponseDto from(AiConsulting aiConsulting) {
        return AiConsultingResponseDto.builder()
            .type(aiConsulting.getType())
            .build();
    }

}
