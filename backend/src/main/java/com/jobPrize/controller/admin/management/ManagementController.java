package com.jobPrize.controller.admin.management;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.management.company.CompanyManagementDetailDto;
import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementDetailDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.admin.management.member.MemberManagementDetailDto;
import com.jobPrize.dto.admin.management.member.MemberManagementSummaryDto;
import com.jobPrize.dto.common.read.IdDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.userManagement.UserManagementService;
import com.jobPrize.service.common.user.UserService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ManagementController {

	private final UserManagementService userManagementService;
	
	private final UserService userService;
	
	@GetMapping("/members")
	public ResponseEntity<Page<MemberManagementSummaryDto>> readMemberPage(Pageable pageable) {
		
		UserType userType = SecurityUtil.getUserType();
		
		Page<MemberManagementSummaryDto> page= userManagementService.readMemberManagementPage(userType, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@GetMapping("/companies")
	public ResponseEntity<Page<CompanyManagementSummaryDto>> readCompanyPage(Pageable pageable){
		
		UserType userType = SecurityUtil.getUserType();
		
		Page<CompanyManagementSummaryDto> page = userManagementService.readCompanyManagementPage(userType, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@GetMapping("/consultants")
	public ResponseEntity<Page<ConsultantManagementSummaryDto>> readConsultantPage(Pageable pageable){
		
		UserType userType = SecurityUtil.getUserType();
		
		Page<ConsultantManagementSummaryDto> page = userManagementService.readConsultantManagementPage(userType, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@PostMapping("/members/detail")
	public ResponseEntity<MemberManagementDetailDto> readMemberDetail(@RequestBody @Valid IdDto targetId){
		
		UserType userType = SecurityUtil.getUserType();
		
		MemberManagementDetailDto dto = userManagementService.readMemberManagement(userType, targetId.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PostMapping("/companies/detail")
	public ResponseEntity<CompanyManagementDetailDto> readCompanyDetail(@RequestBody @Valid IdDto targetId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		CompanyManagementDetailDto dto =userManagementService.readCompanyManagement(userType, targetId.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PostMapping("/consultants/detail")
	public  ResponseEntity<ConsultantManagementDetailDto> readConsultantDetail(@RequestBody @Valid IdDto targetId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		ConsultantManagementDetailDto dto = userManagementService.readConsultantManagement(userType , targetId.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	
	@PostMapping("/deletion")
	public ResponseEntity<Void> deleteUser(@RequestBody @Valid List<IdDto> ids){
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		for(IdDto targetId : ids) {
			userService.softDeleteUser(id, userType, targetId.getId());
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
