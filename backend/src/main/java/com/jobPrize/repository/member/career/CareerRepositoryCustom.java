package com.jobPrize.repository.member.career;

import java.util.List;

import com.jobPrize.entity.member.Career;

public interface CareerRepositoryCustom {
	List<Career> findAllByMemberId(Long id);
}
