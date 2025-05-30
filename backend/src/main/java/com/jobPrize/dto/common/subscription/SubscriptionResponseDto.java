package com.jobPrize.dto.common.subscription;

import java.time.LocalDate;

import com.jobPrize.enumerate.UserType;

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
