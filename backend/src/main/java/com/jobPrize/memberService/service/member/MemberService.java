package com.jobPrize.memberService.service.member;

import com.jobPrize.memberService.dto.member.MemberCreateDto;
import com.jobPrize.memberService.dto.member.MemberResponseDto;
import com.jobPrize.memberService.dto.member.MemberUpdateDto;

public interface MemberService {
	public void registerMemberInfo(Long id, MemberCreateDto memberCreateDto);
	public void updateMemberInfo(Long id, MemberUpdateDto memberUpdateDto);
	public MemberResponseDto getMemberInfo(Long id);

}
