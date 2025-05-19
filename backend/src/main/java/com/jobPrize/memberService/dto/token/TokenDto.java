package com.jobPrize.memberService.dto.token;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDto {
	private String accessToken;
	private String refreshToken;
}
