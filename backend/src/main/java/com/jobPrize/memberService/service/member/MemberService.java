package com.jobPrize.memberService.service.member;

import com.jobPrize.memberService.dto.member.MemberCreateDto;
import com.jobPrize.memberService.dto.member.MemberResponseDto;
import com.jobPrize.memberService.dto.member.MemberUpdateDto;

public interface MemberService {
	public void registerMemberInfo(MemberCreateDto memberCreateDto, String token);
	public void updateMemberInfo(MemberUpdateDto memberUpdateDto, String token);
	public MemberResponseDto getMemberInfo(String token);

}
