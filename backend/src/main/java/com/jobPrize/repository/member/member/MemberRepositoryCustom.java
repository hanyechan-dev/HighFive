package com.jobPrize.repository.member.member;

import java.util.Optional;

import com.jobPrize.entity.member.Member;

public interface MemberRepositoryCustom{
	Optional<Member> findByIdAndDeletedDateIsNull(Long id);
}
