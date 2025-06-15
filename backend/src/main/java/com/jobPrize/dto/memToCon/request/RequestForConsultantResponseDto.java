package com.jobPrize.dto.memToCon.request;

import java.time.LocalDate;

import com.jobPrize.entity.memToCon.Request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestForConsultantResponseDto {
    
    private Long id;
    private String targetJob;
    private String targetCompanyName;
    private String consultingType;
    private String requestStatus;
    private LocalDate createdDate;
    private String resumeJson;
    private String coverLetterJson;
    private String careerDescriptionJson;
    private String nickname;

    public static RequestForConsultantResponseDto from(Request request) {
        return RequestForConsultantResponseDto.builder()
            .id(request.getId())
            .targetJob(request.getTargetJob())
            .targetCompanyName(request.getTargetCompanyName())
            .consultingType(request.getConsultingType().name())
            .requestStatus(request.getRequestStatus().name())
            .createdDate(request.getCreatedDate())
            .resumeJson(request.getResumeJson())
            .coverLetterJson(request.getCoverLetterJson())
            .careerDescriptionJson(request.getCareerDescriptionJson())
            .nickname(request.getMember().getNickname())
            .build();
    }

    


}

