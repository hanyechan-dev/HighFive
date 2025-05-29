package com.jobPrize.controller.consultant.aiConsulting;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.read.IdDto;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.consultant.aiConsulting.AiConsultingService;
import com.jobPrize.service.consultant.consultantConsulting.ConsultantConsultingService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("ai-consultings") 
@RequiredArgsConstructor
public class AiConsultingController {

    private final AiConsultingService aiConsultingService;
    
    private final ConsultantConsultingService consultantConsultingService;
    

    @GetMapping
    public ResponseEntity<Page<AiConsultingSummaryDto>> readAiConsultingPage(Pageable pageable) {

    	UserType userType = SecurityUtil.getUserType();
		
        Page<AiConsultingSummaryDto> page = aiConsultingService.readAiConsultingPage(userType,pageable);
        
        return ResponseEntity.status(200).body(page);
    }
    
	@PostMapping("/approval")
	public ResponseEntity<Void> approveConsulting(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		consultantConsultingService.approveConsulting(id, userType, idDto.getId());
		
		return ResponseEntity.status(201).build();
	}

 
    @PostMapping("/edits/detail")
    public ResponseEntity<AiEditDetailResponseDto> readMyEditDetail(@RequestBody @Valid IdDto idDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
        AiEditDetailResponseDto aiEditDetailResponseDto = aiConsultingService.readEditDetail(id, userType, idDto.getId());
        
        return ResponseEntity.status(200).body(aiEditDetailResponseDto);
    }

 
    @PostMapping("/feedbacks/detail")
    public ResponseEntity<AiFeedbackDetailResponseDto> readMyFeedbackDetail(@RequestBody @Valid IdDto idDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
        AiFeedbackDetailResponseDto aiFeedbackDetailResponseDto = aiConsultingService.readFeedbackDetail(id, userType, idDto.getId());
        
        return ResponseEntity.status(200).body(aiFeedbackDetailResponseDto);
    }
    
    
    
    
    
    
}
