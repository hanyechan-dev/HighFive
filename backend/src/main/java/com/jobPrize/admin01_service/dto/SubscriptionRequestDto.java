package com.jobPrize.admin01_service.dto;

import com.jobPrize.entity.common.UserType;
import com.sun.istack.NotNull;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequestDto {
    @NotBlank(message = "구독자 ID는 비어 있을 수 없습니다.")
    private Long userId;
    
    @NotNull
    private UserType userType;
}
