package com.jobPrize.controller.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.service.memToCom.interest.InterestService;
import com.jobPrize.util.SecurityUtil;

@RestController
@RequestMapping("/interest")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PostMapping("/toggle")
    public ResponseEntity<Void> toggleInterest(@RequestParam Long memberId) {
        Long companyId = SecurityUtil.getId(); 

        interestService.toggleInterest(companyId, memberId); 

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
    }
}