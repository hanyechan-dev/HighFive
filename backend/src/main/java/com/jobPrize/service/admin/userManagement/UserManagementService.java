package com.jobPrize.service.admin.userManagement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.admin.management.company.CompanyManagementDetailDto;
import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementDetailDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.admin.management.member.MemberManagementDetailDto;
import com.jobPrize.dto.admin.management.member.MemberManagementSummaryDto;
import com.jobPrize.entity.common.UserType;

public interface UserManagementService {
	
	Page<MemberManagementSummaryDto> readMemberManagementPage(UserType userType, Pageable pageable);

	Page<CompanyManagementSummaryDto> readCompanyManagementPage(UserType userType, Pageable pageable);

	Page<ConsultantManagementSummaryDto> readConsultantManagementPage(UserType userType, Pageable pageable);

	MemberManagementDetailDto readMemberManagement(UserType userType,Long targetId);

	CompanyManagementDetailDto readCompanyManagement(UserType userType,Long targetId);

	ConsultantManagementDetailDto readConsultantManagement(UserType userType,Long targetId);
	
	Page<CompanyManagementSummaryDto> readWatingCompanyManagementPage(UserType userType, Pageable pageable);

	Page<ConsultantManagementSummaryDto> readWatingConsultantManagementPage(UserType userType, Pageable pageable);
}
