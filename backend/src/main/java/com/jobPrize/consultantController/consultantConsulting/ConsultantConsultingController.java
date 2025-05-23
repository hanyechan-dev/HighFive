package com.jobPrize.consultantController.consultantConsulting;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.consultant.consultantConsulting.ConsultantConsultingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consultant/consultant-consulting")
@RequiredArgsConstructor
public class ConsultantConsultingController {
	
	private final ConsultantConsultingService consultantConsultingService;
	
	@PostMapping("/approve")
	public ResponseEntity<String> approveConsulting 
	(@RequestBody Long aiConsultingId) {
		Long id = SecurityUtil.getId();
		UserType userType = SecurityUtil.getUserType();
		consultantConsultingService.approveConsulting(id, userType, aiConsultingId);
		return ResponseEntity.ok("승인이 완료되었습니다.");
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateConsultantConsulting
	(@RequestBody ConsultantConsultingUpdateDto consultantConsultingUpdateDto) {
	
		Long id = SecurityUtil.getId();
		consultantConsultingService.updateConsultantConsulting(id,consultantConsultingUpdateDto);
		return ResponseEntity.ok("컨설팅 내용이 저장되었습니다.");
	}
	
	@PutMapping("/complete")
	public ResponseEntity<String> completeConsulting
	(@RequestParam("consultantConsultingId") Long consultantConsultingId) {
		
		Long id = SecurityUtil.getId();
		consultantConsultingService.completeConsulting(id, consultantConsultingId);
		return ResponseEntity.ok("컨설팅이 완료되었습니다.");
	}
	
	@GetMapping("/list")
	public ResponseEntity<Page<ConsultantConsultingSummaryDto>> readConsultantConsultingPage(Pageable pageable) {
	    Long id = SecurityUtil.getId();  
	    Page<ConsultantConsultingSummaryDto> page = 
	        consultantConsultingService.readConsultantConsultingPage(id, pageable);
	    return ResponseEntity.ok(page);
	}
	
	@PostMapping("/edit-detail")
	public ResponseEntity<ConsultantEditDetailResponseDto> readEditDetail
	(@RequestBody Long consultantConsultingId) {
		Long id = SecurityUtil.getId();
		ConsultantEditDetailResponseDto consultantEditDetailResponseDto = consultantConsultingService.readEditDetail(id, consultantConsultingId);
		return ResponseEntity.ok(consultantEditDetailResponseDto);
				
	}
	
	@PostMapping("/feedback-detail")
	public ResponseEntity<ConsultantFeedBackDetailResponseDto> readFeedBackDetail
	(@RequestBody Long consultantConsultingId) {
		Long id = SecurityUtil.getId();
		ConsultantFeedBackDetailResponseDto consultantFeedBackDetailResponseDto = consultantConsultingService.readFeedbackDetail(id, consultantConsultingId);
		return ResponseEntity.ok(consultantFeedBackDetailResponseDto);
	}
	
	

	
	
}
