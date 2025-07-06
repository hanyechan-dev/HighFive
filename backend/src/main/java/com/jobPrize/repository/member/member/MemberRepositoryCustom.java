package com.jobPrize.repository.member.member;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.Member;

public interface MemberRepositoryCustom{
	Optional<Member> findByIdAndDeletedDateIsNull(Long id);
	List<Member> findAllByUpdateTimeWithinOneHour();
	List<Member> findAll();
}
