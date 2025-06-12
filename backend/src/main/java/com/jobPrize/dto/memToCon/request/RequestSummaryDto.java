package com.jobPrize.dto.memToCon.request;

import java.time.LocalDate;

import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.enumerate.RequestStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestSummaryDto {
    
    private Long id;
    
    private String targetJob;

    private String targetCompanyName;

    private String requestStatus;

    private LocalDate createdDate;

    public static RequestSummaryDto of(Request request, LocalDate date, RequestStatus requestStatus) {
        return RequestSummaryDto.builder()
            .id(request.getId())
            .targetJob(request.getTargetJob())
            .targetCompanyName(request.getTargetCompanyName())
            .requestStatus(requestStatus.name())
            .createdDate(date)
            .build();
    }

    


}

