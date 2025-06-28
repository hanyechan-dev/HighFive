package com.jobPrize.repository.memToCom.similarity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Member;

public interface SimilarityRepository extends JpaRepository<Similarity, Long>, SimilarityRepositoryCustom {
	boolean existsByMemberAndJobPosting(Member member, JobPosting jobPosting);
	void deleteByMember(Member member);
	void deleteByJobPosting(JobPosting jobPosting);
}
