package com.jobPrize.dto.common.myPage;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponseDto {

	private String email;

	private String name;
	
	private LocalDate birthDate;

	private GenderType genderType;

	private String phone;

	private String address;

	private UserType type;

	public static MyPageResponseDto from(User user) {

		return MyPageResponseDto.builder()
			.email(user.getEmail())
			.name(user.getName())
			.birthDate(user.getBirthDate())
			.genderType(user.getGenderType())
			.phone(user.getPhone())
			.address(user.getAddress())
			.type(user.getType())
			.build();
	}
	
	
}
