package com.jobPrize.admin02.controller.management.controller;

import java.util.List;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.management.company.CompanyManagementDetailDto;
import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementDetailDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.admin.management.member.MemberManagementDetailDto;
import com.jobPrize.dto.admin.management.member.MemberManagementSummaryDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.userManagement.UserManagementService;
import com.jobPrize.service.common.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/user-management")
@RequiredArgsConstructor
public class ManagementController {

	private final UserManagementService userManagementService;
	
	private final UserService userService;
	
	
 
	@GetMapping("/members")
	public ResponseEntity<Page<MemberManagementSummaryDto>> readMemberPage(Pageable pageable) {
		UserType userType = SecurityUtil.getUserType();
		Page<MemberManagementSummaryDto> page= userManagementService.readMemberManagementPage(userType, pageable);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(page);
	}
	
	@GetMapping("/company")
	public ResponseEntity<Page<CompanyManagementSummaryDto>> readCompanyPage(Pageable pageable){
		UserType userType = SecurityUtil.getUserType();
		Page<CompanyManagementSummaryDto> page = userManagementService.readCompanyManagementPage(userType, pageable);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(page);
	}
	
	@GetMapping("/consultant")
	public ResponseEntity<Page<ConsultantManagementSummaryDto>> readConsultantPage(Pageable pageable){
		UserType userType = SecurityUtil.getUserType();
		Page<ConsultantManagementSummaryDto> page = userManagementService.readConsultantManagementPage(userType, pageable);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(page);
				
	}
	
	@GetMapping("/members-detail/{targetId}")
	public ResponseEntity<MemberManagementDetailDto> readMemberDetail(@PathVariable Long targetId){
		UserType userType = SecurityUtil.getUserType();
		MemberManagementDetailDto dto = userManagementService.readMemberManagement(userType, targetId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dto);
	}
	
	@GetMapping("/company-detail/{targetId}")
	public ResponseEntity<CompanyManagementDetailDto> readCompanyDetail(@PathVariable Long targetId) {
		UserType userType = SecurityUtil.getUserType();
		CompanyManagementDetailDto dto =userManagementService.readCompanyManagement(userType, targetId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dto);
	}
	
	@GetMapping("/consultant-detail/{targetId}")
	public  ResponseEntity<ConsultantManagementDetailDto> readConsultantDetail(@PathVariable Long targetId) {
		UserType userType = SecurityUtil.getUserType();
		ConsultantManagementDetailDto dto = userManagementService.readConsultantManagement(userType , targetId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dto);
	}
	
	@GetMapping("/approve-waiting-company")
	public ResponseEntity<Page<CompanyManagementSummaryDto>> readWaitingCompanyPage(Pageable pageable){
		UserType userType = SecurityUtil.getUserType();
		Page<CompanyManagementSummaryDto> page= userManagementService.readWatingCompanyManagementPage(userType, pageable);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(page);
	}
	
	@GetMapping("/approve-waiting-consultant")
	public ResponseEntity <Page<ConsultantManagementSummaryDto>> readWaitingConsultantPage(Pageable pageable){
		UserType userType = SecurityUtil.getUserType();
		Page<ConsultantManagementSummaryDto> page = userManagementService.readWatingConsultantManagementPage(userType, pageable);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(page);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@RequestBody List<Long> ids){
		
		UserType userType = SecurityUtil.getUserType();
		
		for(Long id : ids) {
			userService.softDeleteUser(id);
		}
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
	

}
