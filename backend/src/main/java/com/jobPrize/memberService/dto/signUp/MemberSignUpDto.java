package com.jobPrize.memberService.dto.signUp;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.validation.password.Password;
import com.jobPrize.validation.phone.Phone;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberSignUpDto {
	
	@NotBlank(message = "이메일은 필수로 입력해야합니다.")
	@Email(message = "이메일 형식과 일치하지 않습니다.")
	@Size(max=30, message = "이메일은 30자 이하로 입력해야합니다.")
	private String email;

	@Password
	private String password;
	
	@NotBlank(message = "이름은 필수로 입력해야합니다")
	@Size(max=10, message = "이름은 10자 이하로 입력해야합니다.")
	private String name;

	@NotBlank
	@Phone
	private String phone;

	@NotBlank(message = "주소는 필수로 입력해야합니다")
	@Size(max=50, message = "주소는 50자 이하로 입력해야합니다.")
	private String address;
	
	@NotBlank(message = "닉네임은 필수로 입력해야합니다")
	@Size(max=10, message = "닉네임은 10자 이하로 입력해야합니다.")
	private String nickname;
	
	@NotNull
	private UserType type;
}
