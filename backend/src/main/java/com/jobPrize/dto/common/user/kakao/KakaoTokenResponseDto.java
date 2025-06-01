package com.jobPrize.dto.common.user.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class KakaoTokenResponseDto {
    @JsonProperty("access_token")
    private String accessToken;
}