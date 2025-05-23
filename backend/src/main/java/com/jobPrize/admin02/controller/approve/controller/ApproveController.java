package com.jobPrize.admin02.controller.approve.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.approve.ApproveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/approve")
@RequiredArgsConstructor
public class ApproveController {
	
	private final ApproveService approveService;
	
	@PutMapping("/approve/{id}")
	public String approveUsers(@RequestParam UserType userType, @PathVariable Long id) {
		approveService.approveUser(userType, id);
		return "회원 승인이 완료 되었습니다.";
	}
	
	@PutMapping("/reject/{id}")
	public String unapproveUsers(@RequestParam UserType userType, @PathVariable Long id) {
		approveService.rejectUser(userType, id);
		return "회원 거절이 완료 되었습니다.";
	}

}
