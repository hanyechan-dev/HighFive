package com.jobPrize.dto.memToCon.request;

import java.time.LocalDate;

import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.memToCon.Request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestResponseDto {
    
    private Long id;
    
    private String targetJob;

    private String targetCompanyName;

    private CommonEnum.ConsultingType type;

    private LocalDate createdDate;
    
    private String resumeJson;

    private String coverLetterJson;
    
    private String careerDescriptionJson;

    public static RequestResponseDto from(Request request) {
        return RequestResponseDto.builder()
            .id(request.getId())
            .targetJob(request.getTargetJob())
            .targetCompanyName(request.getTargetCompanyName())
            .type(request.getType())
            .createdDate(request.getCreatedDate())
            .resumeJson(request.getResumeJson())
            .coverLetterJson(request.getCoverLetterJson())
            .careerDescriptionJson(request.getCareerDescriptionJson())
            .build();
    }

    


}

