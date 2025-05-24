package com.jobPrize.repository.memToCom.similarity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.entity.memToCom.Similarity;

public interface SimilarityRepositoryCustom {
	Page<Similarity> findAllWithJobPostingByMemberIdAndCondition(Long id, JobPostingFilterCondition condition, Pageable pageable);
	Page<Similarity> findAllWithMemberByCompanyIdAndCondition(Long id, MemberFilterCondition condition, Pageable pageable);
	
}
