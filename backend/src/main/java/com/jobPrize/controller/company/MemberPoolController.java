package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolDetailDto;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.service.company.memberPool.MemberPoolService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("company/members")
@RequiredArgsConstructor

	public class MemberPoolController {

    private final MemberPoolService memberPoolService ;

    @PostMapping
    public ResponseEntity<Page<MemberPoolSummaryDto>> readMembers(@RequestBody @Valid MemberFilterCondition condition, Pageable pageable) {

        Long id = SecurityUtil.getId();

        Page<MemberPoolSummaryDto> memberPoolSummaryDtos = memberPoolService.readMemberPoolPageByCondition(id, condition, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(memberPoolSummaryDtos);
    }
    
    @GetMapping
    public ResponseEntity<MemberPoolDetailDto> readMember(@RequestParam Long memberId){
    	 
    	Long companyId= SecurityUtil.getId();
    	
    	MemberPoolDetailDto memberPoolDetailDto = memberPoolService.readMemberPoolDetail(memberId, companyId);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(memberPoolDetailDto);
    	
    }
    
}