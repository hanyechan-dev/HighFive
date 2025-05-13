package com.jobPrize.repository.company.similarity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Similarity;

public interface CompanySimilarityRepository extends JpaRepository<Similarity, Long>,CompanySimilarityRepositoryCustom{
	
}
