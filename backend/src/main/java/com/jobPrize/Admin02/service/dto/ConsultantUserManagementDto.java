package com.jobPrize.Admin02.service.dto;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.consultant.Consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantUserManagementDto {
	
	private String email;
	private String phone;
	private String address;
	private LocalDate createdDate;
	
	public static ConsultantUserManagementDto from(Consultant consultant) {
		User user = consultant.getUser();
		return ConsultantUserManagementDto
				.builder()
				.email(user.getEmail())
				.phone(user.getPhone())
				.address(user.getAddress())
				.createdDate(user.getCreatedDate())
				.build();
	}

}
