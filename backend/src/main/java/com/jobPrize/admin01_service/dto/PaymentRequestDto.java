package com.jobPrize.admin01_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    @NotBlank(message = "유저 ID는 필수입니다.")
    private Long userId;

    @NotBlank(message = "결제 금액은 필수입니다.")
    @Positive(message = "결제 금액은 0보다 커야 합니다.")
    private Long paymentAmount;

    @Size(max = 20, message = "결제 수단 정보는 최대 20자까지 입력 가능합니다.")
    private String method;

    @Size(max = 20, message = "결제 수단 정보는 최대 20자까지 입력 가능합니다.")
    private String content;
}
