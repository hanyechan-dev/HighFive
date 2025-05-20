package com.jobPrize.memberService.dto.request;

import java.time.LocalDate;

import com.jobPrize.entity.memToCon.Request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestSummaryDto {
    
    private Long id;
    
    private String targetJob;

    private String targetCompanyName;

    private LocalDate createdDate;

    public static RequestSummaryDto of(Request request, LocalDate date) {
        return RequestSummaryDto.builder()
            .id(request.getId())
            .targetJob(request.getTargetJob())
            .targetCompanyName(request.getTargetCompanyName())
            .createdDate(date)
            .build();
    }

    


}

