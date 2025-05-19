package com.jobPrize.Admin02.service.dto.consultant;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.consultant.Consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantUserManagementDetailDto {
	private String email;
	private String name;
	private String phone;
	private String address;
	private boolean isSubscribed;
	private GenderType genderType;
	private LocalDate birthDate;
	private LocalDate createdDate;
	
	public static ConsultantUserManagementDetailDto from(Consultant consultant) {
		User user = consultant.getUser();
		return ConsultantUserManagementDetailDto
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
