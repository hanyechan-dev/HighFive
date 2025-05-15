package com.jobPrize.repository.memToCom.similarity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Similarity;

public interface SimilarityRepositoryCustom {
	Page<Similarity> findAllWithJobPostingByMemberId(Long id, Pageable pageable);
	Page<Similarity> findAllWithMemberByJobPostingId(Long id, Pageable pageable);
}
