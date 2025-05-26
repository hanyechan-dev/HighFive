package com.jobPrize.admin01_service.dto;

import java.time.LocalDate;

import com.jobPrize.entity.common.UserType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubscriptionResponseDto {
    private Long id;
    private String name;
    private UserType userType;
    private LocalDate startDate;
    private LocalDate endDate;
}
