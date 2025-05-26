package com.jobPrize.admin02.controller.approve.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.approve.ApproveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/approve")
@RequiredArgsConstructor
public class ApproveController {
	
	private final ApproveService approveService;

	@PutMapping("/approve")
	public ResponseEntity<Void> approveUsers(@RequestBody List<Long> ids) {
		
		UserType userType = SecurityUtil.getUserType(); 
		
		for(Long id : ids) {
			approveService.approveUser(userType, id);
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
		
	}

	@PutMapping("/reject")
	public ResponseEntity<Void> unapproveUsers(@RequestBody List<Long> ids) {
		
		UserType userType = SecurityUtil.getUserType();
		
		for(Long id : ids) {
			approveService.rejectUser(userType, id);
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
