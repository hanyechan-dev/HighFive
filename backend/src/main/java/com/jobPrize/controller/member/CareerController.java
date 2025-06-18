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
import com.jobPrize.dto.member.career.CareerCreateDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.career.CareerUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.member.career.CareerService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
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
	public ResponseEntity<CareerResponseDto> createCareer(@RequestBody @Valid CareerCreateDto careerCreateDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		CareerResponseDto careerResponseDto = careerService.createCareer(id, userType, careerCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(careerResponseDto);
	}

	@PutMapping
	public ResponseEntity<CareerResponseDto> updateMyCareer(@RequestBody @Valid CareerUpdateDto careerUpdateDto) {

		Long id = SecurityUtil.getId();

		CareerResponseDto careerResponseDto = careerService.updateCareer(id, careerUpdateDto);

		return ResponseEntity.status(HttpStatus.OK).body(careerResponseDto);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMyCareer(@RequestBody @Valid IdDto idDto) {

		Long id = SecurityUtil.getId();

		careerService.deleteCareer(id, idDto.getId());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
