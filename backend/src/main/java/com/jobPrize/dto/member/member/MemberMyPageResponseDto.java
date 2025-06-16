package com.jobPrize.dto.member.member;

import com.jobPrize.dto.common.myPage.MyPageResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberMyPageResponseDto {
	private MemberResponseDto memberResponseDto;
	private MyPageResponseDto myPageResponseDto;
	
	public static MemberMyPageResponseDto of(MemberResponseDto memberResponseDto, MyPageResponseDto myPageResponseDto) {
		return MemberMyPageResponseDto.builder()
		.memberResponseDto(memberResponseDto)
		.myPageResponseDto(myPageResponseDto)
		.build();
	}
}
