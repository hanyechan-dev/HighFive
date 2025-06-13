package com.jobPrize.controller.consultant;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.consultant.aiConsulting.AiConsultingService;
import com.jobPrize.service.consultant.consultantConsulting.ConsultantConsultingService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai-consultings") 
@RequiredArgsConstructor
public class AiConsultingController {

    private final AiConsultingService aiConsultingService;
    
    private final ConsultantConsultingService consultantConsultingService;
    

    @GetMapping
    public ResponseEntity<Page<AiConsultingSummaryDto>> readAiConsultingPage(Pageable pageable) {

    	UserType userType = SecurityUtil.getUserType();
    	
    	ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();
		
        Page<AiConsultingSummaryDto> page = aiConsultingService.readAiConsultingPage(userType,approvalStatus,pageable);
        
        return ResponseEntity.status(200).body(page);
    }
    
	@PostMapping("/approval")
	public ResponseEntity<Void> approveConsulting(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();
		
		consultantConsultingService.approveConsulting(id, userType, approvalStatus, idDto.getId());
		
		return ResponseEntity.status(201).build();
	}

 
    @PostMapping("/edits/detail")
    public ResponseEntity<AiConsultingDetailResponseDto> readMyEditDetail(@RequestBody @Valid IdDto idDto) {
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();
    	
    	AiConsultingDetailResponseDto aiConsultingDetailResponseDto = aiConsultingService.readEditDetail(userType, approvalStatus, idDto.getId());
        
        return ResponseEntity.status(200).body(aiConsultingDetailResponseDto);
    }

 
    @PostMapping("/feedbacks/detail")
    public ResponseEntity<AiConsultingDetailResponseDto> readMyFeedbackDetail(@RequestBody @Valid IdDto idDto) {
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();
    	
        AiConsultingDetailResponseDto aiFeedbackDetailResponseDto = aiConsultingService.readFeedbackDetail(userType, approvalStatus, idDto.getId());
        
        return ResponseEntity.status(200).body(aiFeedbackDetailResponseDto);
    }
    
    
    
    
    
    
}
