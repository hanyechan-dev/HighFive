package com.jobPrize.memberService.dto.myPage;

import com.jobPrize.validation.password.Password;

import lombok.Getter;

@Getter
public class PasswordUpdateDto {
	
	@Password
	private String password;
	
	@Password
	private String newPassword;
	
	@Password
	private String newPasswordCheck;
}
