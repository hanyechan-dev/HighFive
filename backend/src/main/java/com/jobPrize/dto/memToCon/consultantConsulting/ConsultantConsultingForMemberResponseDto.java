package com.jobPrize.dto.memToCon.consultantConsulting;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.consultant.ConsultantConsulting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantConsultingForMemberResponseDto {
	
    private String consultingType;

    private LocalDate createdDate; // 승인날짜

    private LocalDate completedDate; // 완료날짜
    
    private String name;
    
    private List<ConsultantConsultingContentResponseDto> consultantConsultingContentResponseDtos;
    
    public static ConsultantConsultingForMemberResponseDto of(ConsultantConsulting consultantConsulting, List<ConsultantConsultingContentResponseDto> consultantConsultingContentResponseDtos) {
        return ConsultantConsultingForMemberResponseDto.builder()
        		.consultingType(consultantConsulting.getConsultingType().name())
        		.createdDate(consultantConsulting.getCreatedDate())
        		.completedDate(consultantConsulting.getCreatedDate())
        		.consultantConsultingContentResponseDtos(consultantConsultingContentResponseDtos)
        		.name(consultantConsulting.getConsultant().getUser().getName())
        		.build();
    }

}
