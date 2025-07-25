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
import com.jobPrize.dto.member.coverLetter.CoverLetterCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterSummaryDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.member.coverLetter.CoverLetterService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cover-letters")
@RequiredArgsConstructor
public class CoverLetterController {
	
	private final CoverLetterService coverLetterService;

    @GetMapping
	public ResponseEntity<List<CoverLetterSummaryDto>> readMyCoverLetters() {
		
		Long id = SecurityUtil.getId();
		
		List<CoverLetterSummaryDto> coverLetterSummaryDtos = coverLetterService.readCoverLetterList(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(coverLetterSummaryDtos);
	}
	
	@PostMapping("/detail")
	public ResponseEntity<CoverLetterResponseDto> readMyCoverLetter(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		CoverLetterResponseDto coverLetterResponseDto =coverLetterService.readCoverLetter(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(coverLetterResponseDto);
	}

	@PostMapping
	public ResponseEntity<CoverLetterResponseDto> createCoverLetter(@RequestBody @Valid CoverLetterCreateDto coverLetterCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		CoverLetterResponseDto coverLetterResponseDto = coverLetterService.createCoverLetter(id, userType, coverLetterCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(coverLetterResponseDto);
	}
	
	@PutMapping
	public ResponseEntity<CoverLetterResponseDto> updateMyCoverLetter(@RequestBody @Valid CoverLetterUpdateDto coverLetterUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		CoverLetterResponseDto coverLetterResponseDto = coverLetterService.updateCoverLetter(id, coverLetterUpdateDto);
    
		return ResponseEntity.status(HttpStatus.OK).body(coverLetterResponseDto);
	}
	
	@PostMapping("/deletion")
	public ResponseEntity<Void> deleteMyCoverLetter(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		coverLetterService.deleteCoverLetter(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
