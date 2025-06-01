package com.jobPrize.dto.common.user.kakao;

import lombok.Getter;

@Getter
public class KakaoUserDto {
	
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount {
        private String email;
    }
}