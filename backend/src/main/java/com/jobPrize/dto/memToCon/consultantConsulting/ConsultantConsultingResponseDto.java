package com.jobPrize.dto.memToCon.consultantConsulting;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.consultant.ConsultantConsulting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantConsultingResponseDto {
	
    private String consultingType;

    private LocalDate createdDate; // 승인날짜

    private LocalDate completedDate; // 완료날짜
    
    private List<ConsultantConsultingContentResponseDto> consultantConsultingContentResponseDtos;
    
    public static ConsultantConsultingResponseDto of(ConsultantConsulting consultantConsulting, List<ConsultantConsultingContentResponseDto> consultantConsultingContentResponseDtos) {
        return ConsultantConsultingResponseDto.builder()
        		.consultingType(consultantConsulting.getConsultingType().name())
        		.createdDate(consultantConsulting.getCreatedDate())
        		.completedDate(consultantConsulting.getCreatedDate())
        		.consultantConsultingContentResponseDtos(consultantConsultingContentResponseDtos)
        		.build();
    }

}
