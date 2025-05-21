package com.jobPrize.service.member.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.member.member.MemberCreateDto;
import com.jobPrize.dto.member.member.MemberResponseDto;
import com.jobPrize.dto.member.member.MemberUpdateDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final UserRepository userRepository;
	
	private final MemberRepository memberRepository;

	
	@Override
	public void createMemberInfo(Long id, MemberCreateDto memberCreateDto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		Member member = Member.builder().user(user).nickname(memberCreateDto.getNickname()).build();
		memberRepository.save(member);
		
	}


	@Override
	public void updateMemberInfo(Long id, MemberUpdateDto memberUpdateDto) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		member.updateNickname(memberUpdateDto.getNickname());
		
	}


	@Override
	@Transactional(readOnly = true)
	public MemberResponseDto readMemberInfo(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		String nickname = member.getNickname();
		return MemberResponseDto.builder().nickname(nickname).build();
		
	}









	


}
