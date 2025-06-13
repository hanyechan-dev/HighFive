package com.jobPrize.dto.member.aiConsulting;

import java.util.List;

import com.jobPrize.enumerate.ConsultingType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AiConsultingCreateDto {

    @NotNull(message="컨설팅 종류는 필수로 입력해야합니다.")
    private ConsultingType type;
    
    List<AiConsultingContentCreateDto> aiConsultingContentCreateDtos;

}
