package com.jobPrize.controller.memToCom.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryForMemberDto;
import com.jobPrize.service.memToCom.jobPosting.JobPostingForMemberService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("members/jobPostings")
@RequiredArgsConstructor
public class MemberJobPostingController {

    private final JobPostingForMemberService jobPostingForMemberService;

    @PostMapping
    public ResponseEntity<Page<JobPostingSummaryForMemberDto>> readMyJobPostings(@RequestBody @Valid JobPostingFilterCondition condition, Pageable pageable) {

        Long id = SecurityUtil.getId();

        Page<JobPostingSummaryForMemberDto> jobPostingSummaryForMemberDtos = jobPostingForMemberService.readJobPostingPageByMemberIdAndCondition(id, condition, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(jobPostingSummaryForMemberDtos);
    }

}
