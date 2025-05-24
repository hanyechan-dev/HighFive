package com.jobPrize.repository.member.member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.entity.member.Member;

public interface MemberRepositoryCustom{
	Optional<Member> findWithUserDeletedDateIsNullByMemberId(Long id);
}
