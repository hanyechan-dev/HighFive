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

	private final static String ENTITY_NAME = "회원";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;

	
	@Override
	public void createMemberInfo(Long id, UserType userType, MemberCreateDto memberCreateDto) {

		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		Member member = Member.builder().user(user).nickname(memberCreateDto.getNickname()).build();
		memberRepository.save(member);
		
	}


	@Override
	public void updateMemberInfo(Long id, MemberUpdateDto memberUpdateDto) {

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		member.updateNickname(memberUpdateDto.getNickname());
		
	}


	@Override
	@Transactional(readOnly = true)
	public MemberResponseDto readMemberInfo(Long id) {
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		String nickname = member.getNickname();
		return MemberResponseDto.builder().nickname(nickname).build();
		
	}


}
