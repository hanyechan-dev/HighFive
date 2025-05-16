package com.jobPrize.consultantService.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConsultantCommentRequestDto {

    private Long consultantConsultingId;
    private String item;
    private String comment;              
}
