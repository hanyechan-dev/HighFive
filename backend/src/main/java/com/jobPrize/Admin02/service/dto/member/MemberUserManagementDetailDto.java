package com.jobPrize.Admin02.service.dto.member;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUserManagementDetailDto {
	private String email;
	private String name;
	private String nickName;
	private String phone;
	private String address;
	private boolean  isSubscribed;
	private GenderType genderType;
	private LocalDate birthDate;
	private LocalDate createdDate;
	
	public static MemberUserManagementDetailDto from(Member member) {
		User user = member.getUser();
		
		return MemberUserManagementDetailDto
				.builder()
				.email(user.getEmail())
				.name(user.getName())
				.nickName(member.getNickname())
				.phone(user.getPhone())
				.address(user.getAddress())
				.isSubscribed(user.isSubscribed())
				.genderType(user.getGenderType())
				.birthDate(user.getBirthDate())
				.createdDate(user.getCreatedDate())
				.build();
				
	}
	

}
