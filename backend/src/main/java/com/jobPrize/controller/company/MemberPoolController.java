package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolDetailDto;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.company.memberPool.MemberPoolService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("companies/members")
@RequiredArgsConstructor
public class MemberPoolController {

    private final MemberPoolService memberPoolService;

    @PostMapping
    public ResponseEntity<Page<MemberPoolSummaryDto>> readMembers(@RequestBody @Valid MemberFilterCondition condition, Pageable pageable) {

        Long id = SecurityUtil.getId();
        
		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();

		boolean isSubscribed = SecurityUtil.isSubscribed();

        Page<MemberPoolSummaryDto> memberPoolSummaryDtos = memberPoolService.readMemberPoolPageByCondition(id, userType, approvalStatus, isSubscribed, condition, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(memberPoolSummaryDtos);
    }
    
    @PostMapping("/detail")
    public ResponseEntity<MemberPoolDetailDto> readMember(@RequestBody @Valid IdDto idDto){
        
		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();

		boolean isSubscribed = SecurityUtil.isSubscribed();
    	
    	MemberPoolDetailDto memberPoolDetailDto = memberPoolService.readMemberPoolDetail(userType, approvalStatus, isSubscribed, idDto.getId());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(memberPoolDetailDto);
    	
    }
    
}