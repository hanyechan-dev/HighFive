package com.jobPrize.repository.member.careerDescription;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.CareerDescription;

public interface CareerDescriptionRepositoryCustom {
	Optional<CareerDescription> findWithCareerDescriptionContentsByCareerDescriptionId(Long id);
	List<CareerDescription> findAllByMemberId(Long id);
	Optional<Long> findMemberIdByCareerDescriptionId(Long id);
}
