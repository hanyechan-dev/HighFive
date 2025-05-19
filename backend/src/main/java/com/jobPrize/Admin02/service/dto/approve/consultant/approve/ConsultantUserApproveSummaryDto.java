package com.jobPrize.Admin02.service.dto.approve.consultant.approve;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.consultant.Consultant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultantUserApproveSummaryDto {
	private String email;
	private String name;
	private String phone;
	private String address;
	private LocalDate createdDate;
	
	public static ConsultantUserApproveSummaryDto from(Consultant consultant){
		User user = consultant.getUser();
		return ConsultantUserApproveSummaryDto
				.builder()
				.email(user.getEmail())
				.name(user.getName())
				.phone(user.getPhone())
				.address(user.getAddress())
				.createdDate(user.getCreatedDate())
				.build();
	}

}
