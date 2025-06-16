package com.jobPrize.dto.member.aiConsulting;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AiConsultingCreateDto {

    @NotNull(message="컨설팅 종류는 필수로 입력해야합니다.")
    private String consultingType;
    
    List<AiConsultingContentCreateDto> aiConsultingContentCreateDtos;

}
