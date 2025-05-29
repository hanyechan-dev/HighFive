package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolDetailDto;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.service.company.memberPool.MemberPoolService;
import com.jobPrize.service.memToCom.interest.InterestService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("companies/members")
@RequiredArgsConstructor
public class MemberPoolController {

    private final MemberPoolService memberPoolService;
    
	private final InterestService interestService;

    @PostMapping
    public ResponseEntity<Page<MemberPoolSummaryDto>> readMembers(@RequestBody @Valid MemberFilterCondition condition, Pageable pageable) {

        Long id = SecurityUtil.getId();

        Page<MemberPoolSummaryDto> memberPoolSummaryDtos = memberPoolService.readMemberPoolPageByCondition(id, condition, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(memberPoolSummaryDtos);
    }
    
    @PostMapping("/detail")
    public ResponseEntity<MemberPoolDetailDto> readMember(@RequestBody Long memberId){
    	 
    	Long id= SecurityUtil.getId();
    	
    	MemberPoolDetailDto memberPoolDetailDto = memberPoolService.readMemberPoolDetail(memberId, id);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(memberPoolDetailDto);
    	
    }
    
	@GetMapping("/interests")
	public ResponseEntity<Page<MemberPoolSummaryDto>> readInterestedMembers(
			@RequestBody @Valid MemberFilterCondition condition, Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<MemberPoolSummaryDto> memberPoolSummaryDtos = interestService.readInterestedMembers(id, condition,
				pageable);

		return ResponseEntity.status(HttpStatus.OK).body(memberPoolSummaryDtos);
	}
	
	@PutMapping
	public ResponseEntity<Void> toggleInterest(@RequestBody Long memberId) {
		Long id = SecurityUtil.getId();

		interestService.toggleInterest(id, memberId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
    
}