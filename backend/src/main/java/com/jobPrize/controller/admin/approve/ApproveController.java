package com.jobPrize.controller.admin.approve;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.management.company.CompanyManagementSummaryDto;
import com.jobPrize.dto.admin.management.consultant.ConsultantManagementSummaryDto;
import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.approve.ApproveService;
import com.jobPrize.service.admin.userManagement.UserManagementService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class ApproveController {
	
	private final ApproveService approveService;
	
	private final UserManagementService userManagementService;
	

	
	@GetMapping("/companies")
	public ResponseEntity<Page<CompanyManagementSummaryDto>> readWaitingCompanyPage(Pageable pageable){
		
		UserType userType = SecurityUtil.getUserType();
		
		Page<CompanyManagementSummaryDto> page= userManagementService.readWatingCompanyManagementPage(userType, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@GetMapping("/consultants")
	public ResponseEntity <Page<ConsultantManagementSummaryDto>> readWaitingConsultantPage(Pageable pageable){
		
		UserType userType = SecurityUtil.getUserType();
		
		Page<ConsultantManagementSummaryDto> page = userManagementService.readWatingConsultantManagementPage(userType, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	
	@PutMapping("/approval")
	public ResponseEntity<Void> approveUsers(@RequestBody @Valid List<IdDto> ids) {
		
		UserType userType = SecurityUtil.getUserType(); 

		
		for(IdDto id : ids) {
			approveService.approveUser(userType, id.getId());
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
	}

	@PutMapping("/rejection")
	public ResponseEntity<Void> unapproveUsers(@RequestBody @Valid List<IdDto> ids) {
		
		UserType userType = SecurityUtil.getUserType();
		
		for(IdDto id : ids) {
			approveService.rejectUser(userType, id.getId());
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
