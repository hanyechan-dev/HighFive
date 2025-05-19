package com.jobPrize.Admin02.service.dto.consultant;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.consultant.Consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantUserManagementSummaryDto {
	
	private String email;
	private String phone;
	private String address;
	private LocalDate createdDate;
	
	public static ConsultantUserManagementSummaryDto from(Consultant consultant) {
		User user = consultant.getUser();
		return ConsultantUserManagementSummaryDto
				.builder()
				.email(user.getEmail())
				.phone(user.getPhone())
				.address(user.getAddress())
				.createdDate(user.getCreatedDate())
				.build();
	}

}
