package com.jobPrize.dto.common.user.kakao;

import java.time.LocalDate;

import com.jobPrize.enumerate.GenderType;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.validation.phone.Phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class KakaoUserSignUpDto {
	
	private String email;
	
	@NotBlank(message = "이름은 필수로 입력해야합니다")
	@Size(max=10, message = "이름은 10자 이하로 입력해야합니다.")
	private String name;
	
	@NotNull(message = "생년월일은 필수로 입력해야합니다")
	private LocalDate birthDate;
	
	@NotNull(message = "성별은 필수입니다.")
	private String genderType;

	@NotBlank(message = "전화번호는 필수로 입력해야합니다")
	@Phone
	private String phone;

	@NotBlank(message = "주소는 필수로 입력해야합니다")
	@Size(max=50, message = "주소는 50자 이하로 입력해야합니다.")
	private String address;

	@NotNull(message = "회원 유형은 필수입니다.")
	private String userType;

}
