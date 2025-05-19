package com.jobPrize.memberService.service.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.member.Member;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.memberService.dto.member.MemberCreateDto;
import com.jobPrize.memberService.dto.member.MemberResponseDto;
import com.jobPrize.memberService.dto.member.MemberUpdateDto;
import com.jobPrize.memberService.service.member.MemberService;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final UserRepository userRepository;
	
	private final MemberRepository memberRepository;
	
	private final TokenProvider tokenProvider;
	
	@Override
	public void registerMemberInfo(MemberCreateDto memberCreateDto, String token) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		Member member = Member.builder().user(user).nickname(memberCreateDto.getNickname()).build();
		memberRepository.save(member);
		
	}


	@Override
	public void updateMemberInfo(MemberUpdateDto memberUpdateDto, String token) {
		Long id = tokenProvider.getIdFromToken(token);
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		member.updateNickname(memberUpdateDto.getNickname());
		
	}


	@Override
	public MemberResponseDto getMemberInfo(String token) {
		Long id = tokenProvider.getIdFromToken(token);
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		String nickname = member.getNickname();
		return MemberResponseDto.builder().nickname(nickname).build();
		
	}









	


}
