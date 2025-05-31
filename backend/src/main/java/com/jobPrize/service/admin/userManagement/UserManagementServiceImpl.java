package com.jobPrize.service.admin.userManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.management.company.CompanyManagementDetailDto;
import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementDetailDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.admin.management.member.MemberManagementDetailDto;
import com.jobPrize.dto.admin.management.member.MemberManagementSummaryDto;
import com.jobPrize.dto.admin.management.user.UserManagementDetailDto;
import com.jobPrize.dto.admin.management.user.UserManagementSummaryDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService{
	
	private final UserRepository userRepository;
	
	private final AssertUtil assertUtil;

	private final String ENTITY_NAME = "유저";

	private final UserType ALLOWED_USER_TYPE = UserType.관리자;

	@Override
	public Page<MemberManagementSummaryDto> readMemberManagementPage(UserType userType, Pageable pageable) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Page<User> users = userRepository.findAllWithSubEntityByUserType(UserType.일반회원, pageable);
		List<MemberManagementSummaryDto> memberManagementSummaryDtos = new ArrayList<>();
		
		for(User user : users) {
			
			UserManagementSummaryDto userManagementSummaryDto = UserManagementSummaryDto.from(user);
			
			MemberManagementSummaryDto memberManagementSummaryDto = MemberManagementSummaryDto.of(user.getMember(),userManagementSummaryDto);
			
			memberManagementSummaryDtos.add(memberManagementSummaryDto);
		}
		
		return new PageImpl<MemberManagementSummaryDto>(memberManagementSummaryDtos,pageable,users.getTotalElements());
	}

	@Override
	public Page<CompanyManagementSummaryDto> readCompanyManagementPage(UserType userType, Pageable pageable) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Page<User> users = userRepository.findAllWithSubEntityByUserType(UserType.기업회원, pageable);
		List<CompanyManagementSummaryDto> companyManagementSummaryDtos = new ArrayList<>();
		
		for(User user : users) {
			
			UserManagementSummaryDto userManagementSummaryDto = UserManagementSummaryDto.from(user);
			
			CompanyManagementSummaryDto companyManagementSummaryDto = CompanyManagementSummaryDto.of(user.getCompany(),userManagementSummaryDto);
			
			companyManagementSummaryDtos.add(companyManagementSummaryDto);
		}
		
		return new PageImpl<CompanyManagementSummaryDto>(companyManagementSummaryDtos,pageable,users.getTotalElements());
	}

	@Override
	public Page<ConsultantManagementSummaryDto> readConsultantManagementPage(UserType userType, Pageable pageable) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Page<User> users = userRepository.findAllWithSubEntityByUserType(UserType.컨설턴트회원, pageable);
		List<ConsultantManagementSummaryDto> consultantManagementSummaryDtos = new ArrayList<>();
		
		for(User user : users) {
			
			UserManagementSummaryDto userManagementSummaryDto = UserManagementSummaryDto.from(user);
			
			ConsultantManagementSummaryDto consultantManagementSummaryDto = ConsultantManagementSummaryDto.of(user.getConsultant(),userManagementSummaryDto);
			
			consultantManagementSummaryDtos.add(consultantManagementSummaryDto);
		}
		
		return new PageImpl<ConsultantManagementSummaryDto>(consultantManagementSummaryDtos,pageable,users.getTotalElements());
	}

	@Override
	public MemberManagementDetailDto readMemberManagement(UserType userType, Long targetId) {
		
		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(()-> new CustomEntityNotFoundException("회원"));
		
		return MemberManagementDetailDto.of(user.getMember(), UserManagementDetailDto.from(user));
	}

	@Override
	public CompanyManagementDetailDto readCompanyManagement(UserType userType, Long targetId) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(()-> new CustomEntityNotFoundException("기업"));
	
		return CompanyManagementDetailDto.of(user.getCompany(), UserManagementDetailDto.from(user));
	}

	@Override
	public ConsultantManagementDetailDto readConsultantManagement(UserType userType, Long targetId) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(()-> new CustomEntityNotFoundException("컨설턴트"));
		
		return ConsultantManagementDetailDto.of(user.getConsultant(), UserManagementDetailDto.from(user));
	}

	@Override
	public Page<CompanyManagementSummaryDto> readWatingCompanyManagementPage(UserType userType, Pageable pageable) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Page<User> users = userRepository.findAllWithSubEntityByUserTypeAndApprovalStatusIsWaiting(UserType.기업회원, pageable);
		List<CompanyManagementSummaryDto> companyManagementSummaryDtos = new ArrayList<>();
		
		for(User user : users) {
			
			UserManagementSummaryDto userManagementSummaryDto = UserManagementSummaryDto.from(user);
			
			CompanyManagementSummaryDto companyManagementSummaryDto = CompanyManagementSummaryDto.of(user.getCompany(),userManagementSummaryDto);
			
			companyManagementSummaryDtos.add(companyManagementSummaryDto);
		}
		
		return new PageImpl<CompanyManagementSummaryDto>(companyManagementSummaryDtos,pageable,users.getTotalElements());
	}

	@Override
	public Page<ConsultantManagementSummaryDto> readWatingConsultantManagementPage(UserType userType, Pageable pageable) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Page<User> users = userRepository.findAllWithSubEntityByUserTypeAndApprovalStatusIsWaiting(UserType.컨설턴트회원, pageable);
		List<ConsultantManagementSummaryDto> consultantManagementSummaryDtos = new ArrayList<>();
		
		for(User user : users) {
			
			UserManagementSummaryDto userManagementSummaryDto = UserManagementSummaryDto.from(user);
			
			ConsultantManagementSummaryDto consultantManagementSummaryDto = ConsultantManagementSummaryDto.of(user.getConsultant(),userManagementSummaryDto);
			
			consultantManagementSummaryDtos.add(consultantManagementSummaryDto);
		}
		
		return new PageImpl<ConsultantManagementSummaryDto>(consultantManagementSummaryDtos,pageable,users.getTotalElements());
	}

}
