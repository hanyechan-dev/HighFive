package com.jobPrize.repository.company.company;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobPrize.entity.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {
    
}