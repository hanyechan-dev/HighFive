package com.jobPrize.repository.member.career;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.Career;

public interface CareerRepositoryCustom {
	List<Career> findAllByMemberId(Long id);

	Optional<Long> findMemberIdByCareerId(Long id);
}
