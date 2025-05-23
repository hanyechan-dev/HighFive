package com.jobPrize.admin02.controller.management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.management.company.CompanyManagementDetailDto;
import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementDetailDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.admin.management.member.MemberManagementDetailDto;
import com.jobPrize.dto.admin.management.member.MemberManagementSummaryDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.userManagement.UserManagementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/user-management")
@RequiredArgsConstructor
public class ManagementController {


	private final UserManagementService userManagementService;

 

	@GetMapping("/members")
	public Page<MemberManagementSummaryDto> readMemberPage(@RequestParam UserType userType, Pageable pageable) {
		return userManagementService.readMemberManagementPage(userType, pageable);
	}
	
	@GetMapping("/company")
	public Page<CompanyManagementSummaryDto> readCompanyPage(@RequestParam UserType userType, Pageable pageable){
		return userManagementService.readCompanyManagementPage(userType, pageable);
	}
	
	@GetMapping("/consultant")
	public Page<ConsultantManagementSummaryDto> readConsultantPage(@RequestParam UserType userType, Pageable pageable){
		return userManagementService.readConsultantManagementPage(userType, pageable);
	}
	
	@GetMapping("/members-detail/{targetId}")
	public MemberManagementDetailDto readMemberDetail(@RequestParam UserType userType, @PathVariable Long targetId){
		return userManagementService.readMemberManagement(userType, targetId);
	}
	
	@GetMapping("/company-detail/{targetId}")
	public CompanyManagementDetailDto readCompanyDetail(@RequestParam UserType userType, @PathVariable Long targetId) {
		return userManagementService.readCompanyManagement(userType, targetId);
	}
	
	@GetMapping("/consultant-detail/{targetId}")
	public ConsultantManagementDetailDto readConsultantDetail(@RequestParam UserType userType, @PathVariable Long targetId) {
		return userManagementService.readConsultantManagement(userType , targetId);
	}
	
	@GetMapping("/approve-wating-company")
	public Page<CompanyManagementSummaryDto> readWatingCompanyPage(@RequestParam UserType userType, Pageable pageable){
		return userManagementService.readWatingCompanyManagementPage(userType, pageable);
	}
	
	@GetMapping("/approve-wating-consultant")
	public Page<ConsultantManagementSummaryDto> reqdWatingConsultantPage(@RequestParam UserType userType, Pageable pageable){
		return userManagementService.readWatingConsultantManagementPage(userType, pageable);
	}
	

}
