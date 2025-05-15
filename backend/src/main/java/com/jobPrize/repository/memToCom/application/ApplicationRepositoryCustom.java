package com.jobPrize.repository.memToCom.application;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Application;

public interface ApplicationRepositoryCustom {
	Page<Application> findAllByMemberId(Long id, Pageable pageable);
	Page<Application> findAllByJobPostingId(Long id, Pageable pageable);
	Optional<Application> findByApplicationId(Long id);
}
