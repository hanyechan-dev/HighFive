package com.jobPrize.controller.member.career;

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
import com.jobPrize.dto.member.career.CareerCreateDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.career.CareerUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.member.career.CareerService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/careers")
@RequiredArgsConstructor
public class CareerController {
	
	private final CareerService careerService;
	
	

	@GetMapping
	public ResponseEntity<List<CareerResponseDto>> readMyCareers() {

		Long id = SecurityUtil.getId();

		List<CareerResponseDto> careerResponseDtos = careerService.readCareerList(id);

		return ResponseEntity.status(HttpStatus.OK).body(careerResponseDtos);
	}

	@PostMapping
	public ResponseEntity<Void> createCareer(@RequestBody CareerCreateDto careerCreateDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		careerService.createCareer(id, userType, careerCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<Void> updateMyCareer(@RequestBody CareerUpdateDto careerUpdateDto) {

		Long id = SecurityUtil.getId();

		careerService.updateCareer(id, careerUpdateDto);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMyCareer(@RequestBody DeleteIdDto deleteIdDto) {

		Long id = SecurityUtil.getId();

		careerService.deleteCareer(id, deleteIdDto.getId());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
