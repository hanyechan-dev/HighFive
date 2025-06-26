package com.jobPrize.dto.admin.management.member;

import com.jobPrize.dto.admin.management.user.UserManagementSummaryDto;
import com.jobPrize.entity.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberManagementSummaryDto {
    
	private UserManagementSummaryDto userManagementSummaryDto;

	private String nickName;

    public static MemberManagementSummaryDto of(Member member, UserManagementSummaryDto userManagementSummaryDto){
    	
        return MemberManagementSummaryDto
                .builder()
                .nickName(member.getNickname())
                .userManagementSummaryDto(userManagementSummaryDto)
                .build();
    }
}
