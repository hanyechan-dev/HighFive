package com.jobPrize.controller.member;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.member.certification.CertificationService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
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
	public ResponseEntity<CertificationResponseDto> createCertification(@RequestBody @Valid CertificationCreateDto certificationCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		CertificationResponseDto certificationResponseDto = certificationService.createCertification(id, userType, certificationCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(certificationResponseDto);
	}

	@PutMapping
	public ResponseEntity<CertificationResponseDto> updateMyCertification(@RequestBody @Valid CertificationUpdateDto certificationUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		CertificationResponseDto certificationResponseDto = certificationService.updateCertification(id, certificationUpdateDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(certificationResponseDto);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMyCertification(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		certificationService.deleteCertification(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
