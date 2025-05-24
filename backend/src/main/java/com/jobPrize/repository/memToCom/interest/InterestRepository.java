package com.jobPrize.repository.memToCom.interest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> , InterestRepositoryCustom{
	boolean existsByCompanyIdAndMemberId(Long companyId, Long memberId);
	Optional<Interest> findByCompanyIdAndMemberId(Long companyId, Long memberId);
}
