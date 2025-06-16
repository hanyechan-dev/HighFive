package com.jobPrize.dto.common.myPage;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.GenderType;
import com.jobPrize.enumerate.UserType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponseDto {

	private String email;

	private String name;
	
	private LocalDate birthDate;

	private String genderType;

	private String phone;

	private String address;


	public static MyPageResponseDto from(User user) {

		return MyPageResponseDto.builder()
			.email(user.getEmail())
			.name(user.getName())
			.birthDate(user.getBirthDate())
			.genderType(user.getGenderType().name())
			.phone(user.getPhone())
			.address(user.getAddress())
			.build();
	}
	
	
}
