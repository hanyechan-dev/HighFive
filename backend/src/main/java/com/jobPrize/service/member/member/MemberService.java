package com.jobPrize.service.member.member;

import com.jobPrize.dto.member.member.MemberCreateDto;
import com.jobPrize.dto.member.member.MemberResponseDto;
import com.jobPrize.dto.member.member.MemberUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface MemberService {
	public void createMemberInfo(Long id, UserType userType, MemberCreateDto memberCreateDto);
	public MemberResponseDto updateMemberInfo(Long id, MemberUpdateDto memberUpdateDto);
	public MemberResponseDto readMemberInfo(Long id);
	public void calcVector();

}
