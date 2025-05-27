package com.jobPrize.controller.member.careerDescription;

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
import com.jobPrize.dto.common.read.ReadIdDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionSummaryDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.member.careerDescription.CareerDescriptionService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/career-descriptions")
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
	public ResponseEntity<CareerDescriptionResponseDto> readMyCareerDescription(@RequestBody ReadIdDto readIdDto) {
		
		Long id = SecurityUtil.getId();
		
		CareerDescriptionResponseDto careerDescriptionResponseDto = careerDescriptionService.readCareerDescription(id, readIdDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(careerDescriptionResponseDto);
	}

	@PostMapping
	public ResponseEntity<Void> createCareerDescription(@RequestBody CareerDescriptionCreateDto careerDescriptionCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		careerDescriptionService.createCareerDescription(id, userType, careerDescriptionCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> updateMyCareerDescription(@RequestBody CareerDescriptionUpdateDto careerDescriptionUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		careerDescriptionService.updateCareerDescription(id, careerDescriptionUpdateDto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping("/deletion")
	public ResponseEntity<Void> deleteMyCareerDescription(@RequestBody DeleteIdDto deleteIdDto) {
		
		Long id = SecurityUtil.getId();
		
		careerDescriptionService.deleteCareerDescription(id, deleteIdDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
