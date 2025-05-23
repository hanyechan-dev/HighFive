package com.jobPrize.service.company.company;

import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;

public interface CompanyService {
	void createCompanyInfo(Long id, CompanyCreateDto companyCreateDto);
	CompanyResponseDto readCompanyInfo(Long id);
	void updateCompanyInfo(Long id, CompanyUpdateDto companyUpdateDto);
}
