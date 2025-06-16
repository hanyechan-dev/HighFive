package com.jobPrize.repository.memToCom.similarity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.entity.memToCom.Similarity;

public interface SimilarityRepositoryCustom {
	Page<Similarity> findAllWithJobPostingByMemberIdAndCondition(Long id, JobPostingFilterCondition condition, Pageable pageable);
	List<Similarity> findFirst4WithJobPostingByMemberId(Long id);
	List<Similarity> findSecond8WithJobPostingByMemberIdAndCondition(Long id);
	Page<Similarity> findAllWithMemberByCompanyIdAndCondition(Long id, MemberFilterCondition condition, Pageable pageable);
	
}
