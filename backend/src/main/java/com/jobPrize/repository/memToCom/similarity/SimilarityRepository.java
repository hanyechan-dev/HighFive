package com.jobPrize.repository.memToCom.similarity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Similarity;

public interface SimilarityRepository extends JpaRepository<Similarity, Long>, SimilarityRepositoryCustom {
}
