package com.jobPrize.repository.company.company;

import java.util.Optional;

import com.jobPrize.entity.company.Company;

public interface CompanyRepositoryCustom {
	Optional<Company> findByIdAndDeletedDateIsNull(Long id);
}