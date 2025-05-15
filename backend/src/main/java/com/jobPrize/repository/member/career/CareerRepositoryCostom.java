package com.jobPrize.repository.member.career;

import java.util.List;

import com.jobPrize.entity.member.Career;

public interface CareerRepositoryCostom {
	List<Career> findAllByMemberId(Long id);
}
