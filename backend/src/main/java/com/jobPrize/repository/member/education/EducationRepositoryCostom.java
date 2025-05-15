package com.jobPrize.repository.member.education;

import java.util.List;

import com.jobPrize.entity.member.Education;

public interface EducationRepositoryCostom {
	List<Education> findAllByMemberId(Long id);
}
