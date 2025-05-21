package com.jobPrize.service.admin.userManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.admin.management.company.CompanyManagementDetailDto;
import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementDetailDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.admin.management.member.MemberManagementDetailDto;
import com.jobPrize.dto.admin.management.member.MemberManagementSummaryDto;
import com.jobPrize.dto.admin.management.user.UserManagementDetailDto;
import com.jobPrize.dto.admin.management.user.UserManagementSummaryDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService{
	
	private final UserRepository userRepository;

	@Override
	public Page<MemberManagementSummaryDto> readMemberManagementPage(UserType userType, Pageable pageable) {
		validateAdmin(userType);
		
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
		validateAdmin(userType);
		
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
		validateAdmin(userType);
		
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
		validateAdmin(userType);
		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(()-> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
		
		
		return MemberManagementDetailDto.of(user.getMember(), UserManagementDetailDto.from(user));
	}

	@Override
	public CompanyManagementDetailDto readCompanyManagement(UserType userType, Long targetId) {
		validateAdmin(userType);
		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(()-> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
		
		
		return CompanyManagementDetailDto.of(user.getCompany(), UserManagementDetailDto.from(user));
	}

	@Override
	public ConsultantManagementDetailDto readConsultantManagement(UserType userType, Long targetId) {
		validateAdmin(userType);
		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(()-> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
		
		
		return ConsultantManagementDetailDto.of(user.getConsultant(), UserManagementDetailDto.from(user));
	}

	@Override
	public Page<CompanyManagementSummaryDto> readWatingCompanyManagementPage(UserType userType, Pageable pageable) {
		validateAdmin(userType);
		
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
		validateAdmin(userType);
		
		Page<User> users = userRepository.findAllWithSubEntityByUserTypeAndApprovalStatusIsWaiting(UserType.컨설턴트회원, pageable);
		List<ConsultantManagementSummaryDto> consultantManagementSummaryDtos = new ArrayList<>();
		
		for(User user : users) {
			
			UserManagementSummaryDto userManagementSummaryDto = UserManagementSummaryDto.from(user);
			
			ConsultantManagementSummaryDto consultantManagementSummaryDto = ConsultantManagementSummaryDto.of(user.getConsultant(),userManagementSummaryDto);
			
			consultantManagementSummaryDtos.add(consultantManagementSummaryDto);
		}
		
		return new PageImpl<ConsultantManagementSummaryDto>(consultantManagementSummaryDtos,pageable,users.getTotalElements());
	}


	
	
	private void validateAdmin(UserType userType) {
		if (userType != UserType.관리자) {
			throw new AccessDeniedException("관리자만 조회할 수 있습니다.");
		}
	}
			

}
