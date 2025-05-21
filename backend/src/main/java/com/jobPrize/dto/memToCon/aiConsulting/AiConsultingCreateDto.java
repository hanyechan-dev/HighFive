package com.jobPrize.dto.memToCon.aiConsulting;

import com.jobPrize.entity.consultant.CommonEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AiConsultingCreateDto {

    @NotNull(message="문서 종류는 필수로 입력해야합니다.")
    private CommonEnum.DocumentType type;

}
