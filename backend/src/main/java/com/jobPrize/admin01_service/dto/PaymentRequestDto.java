package com.jobPrize.admin01_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PaymentRequestDto {

    @NotBlank(message = "결제 금액은 필수입니다.")
    @Positive(message = "결제 금액은 0보다 커야 합니다.")
    private int paymentAmount;
    
    @Size(max = 20, message = "결제 내용 정보는 최대 20자까지 입력 가능합니다.")
    private String content;

    @Size(max = 20, message = "결제 수단 정보는 최대 20자까지 입력 가능합니다.")
    private String method;
}
