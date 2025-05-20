package com.jobPrize.memberService.dto.myPage;

import com.jobPrize.validation.phone.Phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MyPageUpdateDto {


	@NotBlank(message = "전화번호는 필수로 입력해야합니다")
	@Phone
	private String phone;

	@NotBlank(message = "주소는 필수로 입력해야합니다")
	@Size(max=50, message = "주소는 50자 이하로 입력해야합니다.")
	private String address;

}
