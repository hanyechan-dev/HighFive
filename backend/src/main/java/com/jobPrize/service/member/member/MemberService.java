package com.jobPrize.service.member.member;

import com.jobPrize.dto.member.member.MemberCreateDto;
import com.jobPrize.dto.member.member.MemberResponseDto;
import com.jobPrize.dto.member.member.MemberUpdateDto;

public interface MemberService {
	public void createMemberInfo(Long id, MemberCreateDto memberCreateDto);
	public void updateMemberInfo(Long id, MemberUpdateDto memberUpdateDto);
	public MemberResponseDto readMemberInfo(Long id);

}
