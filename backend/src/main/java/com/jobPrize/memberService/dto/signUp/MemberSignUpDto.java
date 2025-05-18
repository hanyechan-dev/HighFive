package com.jobPrize.memberService.dto.signUp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignUpDto {
	
	@NotBlank(message = "닉네임은 필수로 입력해야합니다")
	@Size(max=10, message = "닉네임은 10자 이하로 입력해야합니다.")
	private String nickname;
	

}
