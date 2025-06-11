package com.jobPrize.controller.consultant;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.consultant.consultantConsulting.ConsultantConsultingService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consultant-consultings")
@RequiredArgsConstructor
public class ConsultantConsultingController {
	
	private final ConsultantConsultingService consultantConsultingService;
	
	@GetMapping
	public ResponseEntity<Page<ConsultantConsultingSummaryDto>> readConsultantConsultingPage(Pageable pageable) {
		
	    Long id = SecurityUtil.getId();  
	    
	    Page<ConsultantConsultingSummaryDto> page = consultantConsultingService.readConsultantConsultingPage(id, pageable);
	    
	    return ResponseEntity.status(200).body(page);
	}
	
	@PostMapping("/edits/detail")
	public ResponseEntity<ConsultantEditDetailResponseDto> readMyEditDetail(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ConsultantEditDetailResponseDto consultantEditDetailResponseDto = consultantConsultingService.readEditDetail(id, userType, idDto.getId());
		
		return ResponseEntity.status(200).body(consultantEditDetailResponseDto);
				
	}
	
	@PostMapping("/feedbacks/detail")
	public ResponseEntity<ConsultantFeedBackDetailResponseDto> readMyFeedBackDetail(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ConsultantFeedBackDetailResponseDto consultantFeedBackDetailResponseDto = consultantConsultingService.readFeedbackDetail(id, userType, idDto.getId());
		return ResponseEntity.status(200).body(consultantFeedBackDetailResponseDto);
	}

	
	@PutMapping
	public ResponseEntity<Void> updateConsultantConsulting(@RequestBody @Valid ConsultantConsultingUpdateDto consultantConsultingUpdateDto) {
	
		Long id = SecurityUtil.getId();
		
		consultantConsultingService.updateConsultantConsulting(id,consultantConsultingUpdateDto);
		
		return ResponseEntity.status(200).build();
	}
	
	@PutMapping("/completion")
	public ResponseEntity<Void> completeConsulting(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		consultantConsultingService.completeConsulting(id, idDto.getId());
		
		return ResponseEntity.status(204).build();
		
	}
	

	

	
	

	
	
}
