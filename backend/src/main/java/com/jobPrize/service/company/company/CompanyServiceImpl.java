package com.jobPrize.service.company.company;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.company.company.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	
	private final UserRepository userRepository;
	
	private final CompanyRepository companyRepository;

	@Override
	public void createCompanyInfo(Long id, CompanyCreateDto companyCreateDto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(()-> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
		
		Company company = Company.of(user,companyCreateDto);
		
		companyRepository.save(company);
		
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyResponseDto readCompanyInfo(Long id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("해당 기업이 존재하지 않습니다."));
		
		return CompanyResponseDto.from(company);
	}

	@Override
	public void updateCompanyInfo(Long id, CompanyUpdateDto companyUpdateDto) {
		Company company = companyRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("해당 기업이 존재하지 않습니다."));
		
		company.updateCompanyInfo(companyUpdateDto);
		
	}

}
