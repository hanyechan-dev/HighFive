package com.jobPrize.service.company.company;

import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface CompanyService {
	void createCompanyInfo(Long id, UserType usertype, CompanyCreateDto companyCreateDto, MultipartFile logoImageFile);
	CompanyResponseDto readCompanyInfo(Long id);
	void updateCompanyInfo(Long id, CompanyUpdateDto companyUpdateDto, MultipartFile logoImageFile);
}
