package com.jobPrize.consultantService.dto;


import com.jobPrize.entity.consultant.CommonEnum.DocumentType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConsultantContentRequestDto {

    private Long consultantConsultingId;
    private String item;
    private String content;
    private DocumentType documentType;
}
