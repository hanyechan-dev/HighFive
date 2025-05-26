package com.jobPrize.controller.consultant.consultantConsulting;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.consultant.consultantConsulting.ConsultantConsultingService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consultant/consultant-consulting")
@RequiredArgsConstructor
public class ConsultantConsultingController {
	
	private final ConsultantConsultingService consultantConsultingService;
	
	@GetMapping("/list")
	public ResponseEntity<Page<ConsultantConsultingSummaryDto>> readConsultantConsultingPage(Pageable pageable) {
		
	    Long id = SecurityUtil.getId();  
	    
	    Page<ConsultantConsultingSummaryDto> page = consultantConsultingService.readConsultantConsultingPage(id, pageable);
	    
	    return ResponseEntity.status(200).body(page);
	}
	
	@PostMapping("/edit-detail")
	public ResponseEntity<ConsultantEditDetailResponseDto> readEditDetail(@RequestBody Long consultantConsultingId) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ConsultantEditDetailResponseDto consultantEditDetailResponseDto = consultantConsultingService.readEditDetail(id, userType, consultantConsultingId);
		
		return ResponseEntity.status(200).body(consultantEditDetailResponseDto);
				
	}
	
	@PostMapping("/feedback-detail")
	public ResponseEntity<ConsultantFeedBackDetailResponseDto> readFeedBackDetail(@RequestBody Long consultantConsultingId) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ConsultantFeedBackDetailResponseDto consultantFeedBackDetailResponseDto = consultantConsultingService.readFeedbackDetail(id, userType, consultantConsultingId);
		return ResponseEntity.status(200).body(consultantFeedBackDetailResponseDto);
	}

	
	@PutMapping("/update")
	public ResponseEntity<Void> updateConsultantConsulting(@RequestBody ConsultantConsultingUpdateDto consultantConsultingUpdateDto) {
	
		Long id = SecurityUtil.getId();
		
		consultantConsultingService.updateConsultantConsulting(id,consultantConsultingUpdateDto);
		
		return ResponseEntity.status(200).build();
	}
	
	@PutMapping("/complete")
	public ResponseEntity<Void> completeConsulting(@RequestBody Long consultantConsultingId) {
		
		Long id = SecurityUtil.getId();
		
		consultantConsultingService.completeConsulting(id, consultantConsultingId);
		
		return ResponseEntity.status(204).build();
		
	}
	

	

	
	

	
	
}
