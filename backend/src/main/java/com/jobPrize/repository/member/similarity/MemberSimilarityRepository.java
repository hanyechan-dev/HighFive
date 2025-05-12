package com.jobPrize.repository.member.similarity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Similarity;

public interface MemberSimilarityRepository extends JpaRepository<Similarity, Long>, MemberSimilarityRepositoryCustom {
}
