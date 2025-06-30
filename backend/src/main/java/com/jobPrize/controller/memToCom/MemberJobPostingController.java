package com.jobPrize.controller.memToCom;

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

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingForMemberResponseDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingMainCardDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryForMemberDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingUnderCardDto;
import com.jobPrize.service.memToCom.jobPosting.JobPostingForMemberService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members/job-postings")
@RequiredArgsConstructor
public class MemberJobPostingController {

    private final JobPostingForMemberService jobPostingForMemberService;
    
    @GetMapping("/mains")
    public ResponseEntity<List<JobPostingMainCardDto>> readMyJobPostingMainCards(){
    	
    	Long id = SecurityUtil.getId();
    	
    	List<JobPostingMainCardDto> jobPostingMainCardDtos = jobPostingForMemberService.readJobPostingMainCardListByMemberId(id);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(jobPostingMainCardDtos);
    }
    
    @GetMapping("/unders")
    public ResponseEntity<List<JobPostingUnderCardDto>> readMyJobPostingUnderCards(){
    	
    	Long id = SecurityUtil.getId();
    	
    	List<JobPostingUnderCardDto> jobPostingUnderCardDtos = jobPostingForMemberService.readJobPostingUnderCardListByMemberId(id);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(jobPostingUnderCardDtos);
    }
    
    
    @PostMapping
    public ResponseEntity<Page<JobPostingSummaryForMemberDto>> readMyJobPostings(@RequestBody @Valid JobPostingFilterCondition condition, Pageable pageable) {

        Long id = SecurityUtil.getId();

        Page<JobPostingSummaryForMemberDto> jobPostingSummaryForMemberDtos = jobPostingForMemberService.readJobPostingPageByMemberIdAndCondition(id, condition, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(jobPostingSummaryForMemberDtos);
    }
    
    @PostMapping("/detail")
	public ResponseEntity<JobPostingForMemberResponseDto> readMyJobPosting(@RequestBody @Valid IdDto IdDto){
		
    	JobPostingForMemberResponseDto jobPostingForMemberResponseDto = jobPostingForMemberService.readJobPosting(IdDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(jobPostingForMemberResponseDto);
	}

}
