package com.jobPrize.dto.admin.management.member;

import com.jobPrize.dto.admin.management.user.UserManagementDetailDto;
import com.jobPrize.entity.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberManagementDetailDto {
	
	private UserManagementDetailDto userManagementDetailDto;

	private String nickName;

	
	public static MemberManagementDetailDto of(Member member, UserManagementDetailDto userManagementDetailDto) {
		
		return MemberManagementDetailDto
				.builder()
				.userManagementDetailDto(userManagementDetailDto)
				.nickName(member.getNickname())
				.build();
				
	}
	

}
