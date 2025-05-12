package com.jobPrize.repository.member.similarity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Similarity;

public interface MemberSimilarityRepositoryCustom {
	Page<Similarity> findSimilaritysByMemberId(Long id, Pageable pageable);
}
