package com.jobPrize.service.member.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.member.MemberCreateDto;
import com.jobPrize.dto.member.member.MemberResponseDto;
import com.jobPrize.dto.member.member.MemberUpdateDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final UserRepository userRepository;
	
	private final MemberRepository memberRepository;

	private final AssertUtil assertUtil;

	
	@Override
	public void createMemberInfo(Long id, UserType userType, MemberCreateDto memberCreateDto) {

		assertUtil.assertUserType(userType, UserType.일반회원, "회원 정보 등록");

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		Member member = Member.builder().user(user).nickname(memberCreateDto.getNickname()).build();
		memberRepository.save(member);
		
	}


	@Override
	public void updateMemberInfo(Long id, MemberUpdateDto memberUpdateDto) {

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		assertUtil.assertId(id, member, "회원 정보 수정");
		
		member.updateNickname(memberUpdateDto.getNickname());
		
	}


	@Override
	@Transactional(readOnly = true)
	public MemberResponseDto readMemberInfo(Long id) {
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		String nickname = member.getNickname();
		return MemberResponseDto.builder().nickname(nickname).build();
		
	}


}
