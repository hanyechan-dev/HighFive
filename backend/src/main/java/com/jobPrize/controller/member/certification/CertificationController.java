package com.jobPrize.controller.member.certification;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.delete.DeleteIdDto;
import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.member.certification.CertificationService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/certifications")
@RequiredArgsConstructor
public class CertificationController {
	
	private final CertificationService certificationService;
	
	@GetMapping
	public ResponseEntity<List<CertificationResponseDto>> readMyCertifications() {
		
		Long id = SecurityUtil.getId();
		
		List<CertificationResponseDto> certificationResponseDtos = certificationService.readCertificationList(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(certificationResponseDtos);
	}

	@PostMapping
	public ResponseEntity<Void> createCertification(@RequestBody CertificationCreateDto certificationCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		certificationService.createCertification(id, userType, certificationCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<Void> updateMyCertification(@RequestBody CertificationUpdateDto certificationUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		certificationService.updateCertification(id, certificationUpdateDto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMyCertification(@RequestBody DeleteIdDto deleteIdDto) {
		
		Long id = SecurityUtil.getId();
		
		certificationService.deleteCertification(id, deleteIdDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
