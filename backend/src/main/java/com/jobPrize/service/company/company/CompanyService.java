package com.jobPrize.service.company.company;

import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface CompanyService {
	void createCompanyInfo(Long id, UserType usertype, CompanyCreateDto companyCreateDto);
	CompanyResponseDto readCompanyInfo(Long id);
	void updateCompanyInfo(Long id, CompanyUpdateDto companyUpdateDto);
}
