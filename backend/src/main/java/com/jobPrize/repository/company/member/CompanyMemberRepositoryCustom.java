package com.jobPrize.repository.company.member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.member.Member;

public interface CompanyMemberRepositoryCustom {
	Page<Member> findMembersByJobPostingIdOrderBySimilarityScore(Long id, Pageable pageable);
	Optional<Member> findMemberById(Long id);
}
