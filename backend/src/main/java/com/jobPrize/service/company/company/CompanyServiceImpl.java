package com.jobPrize.service.company.company;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	
	private final UserRepository userRepository;
	
	private final CompanyRepository companyRepository;

	private final AssertUtil assertUtil;

	private static final String ENTITY_NAME = "기업";

	@Override
	public void createCompanyInfo(Long id , UserType usertype, CompanyCreateDto companyCreateDto) {

		String action = "등록";

		assertUtil.assertUserType(usertype, UserType.기업회원, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(()-> new CustomEntityNotFoundException("유저"));
		
		Company company = Company.of(user,companyCreateDto);
		
		companyRepository.save(company);
		
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyResponseDto readCompanyInfo(Long id) {
		
		Company company = companyRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(()-> new CustomEntityNotFoundException("기업"));
		
		return CompanyResponseDto.from(company);
	}

	@Override
	public void updateCompanyInfo(Long id, CompanyUpdateDto companyUpdateDto) {
		
		Company company = companyRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(()-> new CustomEntityNotFoundException("기업"));
		
		company.updateCompanyInfo(companyUpdateDto);
		
	}

}
