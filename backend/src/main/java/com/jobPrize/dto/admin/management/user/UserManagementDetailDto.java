package com.jobPrize.dto.admin.management.user;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserManagementDetailDto {
	private String email;
	private String name;
	private String phone;
	private String address;
	private boolean  isSubscribed;
	private GenderType genderType;
	private LocalDate birthDate;
	private LocalDate createdDate;
	
	public static UserManagementDetailDto from(User user) {
		
		return UserManagementDetailDto
				.builder()
				.email(user.getEmail())
				.name(user.getName())
				.phone(user.getPhone())
				.address(user.getAddress())
				.isSubscribed(user.isSubscribed())
				.genderType(user.getGenderType())
				.birthDate(user.getBirthDate())
				.createdDate(user.getCreatedDate())
				.build();
				
	}
	

}
