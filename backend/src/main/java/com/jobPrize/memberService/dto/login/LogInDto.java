package com.jobPrize.memberService.dto.login;

import com.jobPrize.validation.password.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LogInDto {
	@NotBlank(message = "이메일은 필수로 입력해야합니다.")
	@Email(message = "이메일 형식과 일치하지 않습니다.")
	@Size(max=30, message = "이메일은 30자 이하로 입력해야합니다.")
	private String email;

	@Password
	private String password;
}
