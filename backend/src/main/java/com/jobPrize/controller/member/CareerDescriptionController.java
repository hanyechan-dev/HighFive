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
import com.jobPrize.dto.member.careerDescription.CareerDescriptionCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionSummaryDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.member.careerDescription.CareerDescriptionService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/career-descriptions")
@RequiredArgsConstructor
public class CareerDescriptionController {
	
	private final CareerDescriptionService careerDescriptionService;
	
	@GetMapping
	public ResponseEntity<List<CareerDescriptionSummaryDto>> readMyCareerDescriptions() {
		
		Long id = SecurityUtil.getId();
		
		List<CareerDescriptionSummaryDto> careerDescriptionSummaryDtos = careerDescriptionService.readCareerDescriptionList(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(careerDescriptionSummaryDtos);
	}
	
	@PostMapping("/detail")
	public ResponseEntity<CareerDescriptionResponseDto> readMyCareerDescription(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		CareerDescriptionResponseDto careerDescriptionResponseDto = careerDescriptionService.readCareerDescription(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(careerDescriptionResponseDto);
	}

	@PostMapping
	public ResponseEntity<CareerDescriptionResponseDto> createCareerDescription(@RequestBody @Valid CareerDescriptionCreateDto careerDescriptionCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		CareerDescriptionResponseDto careerDescriptionResponseDto = careerDescriptionService.createCareerDescription(id, userType, careerDescriptionCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(careerDescriptionResponseDto);
	}
	
	@PutMapping
	public ResponseEntity<CareerDescriptionResponseDto> updateMyCareerDescription(@RequestBody @Valid CareerDescriptionUpdateDto careerDescriptionUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		CareerDescriptionResponseDto careerDescriptionResponseDto = careerDescriptionService.updateCareerDescription(id, careerDescriptionUpdateDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(careerDescriptionResponseDto);
	}
	
	@PostMapping("/deletion")
	public ResponseEntity<Void> deleteMyCareerDescription(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		careerDescriptionService.deleteCareerDescription(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
