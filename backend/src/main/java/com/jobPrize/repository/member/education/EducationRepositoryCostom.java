package com.jobPrize.repository.member.education;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.Education;

public interface EducationRepositoryCostom {
	List<Education> findAllByMemberId(Long id);
	Optional<Long> findMemberIdByEducationId(Long id);
}
