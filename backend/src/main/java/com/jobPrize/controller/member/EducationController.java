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
import com.jobPrize.dto.member.education.EducationCreateDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.education.EducationUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.member.education.EducationService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/educations")
@RequiredArgsConstructor
public class EducationController {
	
	private final EducationService educationService;
	
	@GetMapping
	public ResponseEntity<List<EducationResponseDto>> readMyEducations() {
		
		Long id = SecurityUtil.getId();
		
		List<EducationResponseDto> educationResponseDtos = educationService.readEducationList(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(educationResponseDtos);
	}

	@PostMapping
	public ResponseEntity<EducationResponseDto> createEducation(@RequestBody @Valid EducationCreateDto educationCreateDto) {
		
		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();
		
		EducationResponseDto educationResponseDto = educationService.createEducation(id, userType, educationCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(educationResponseDto);
	}

	@PutMapping
	public ResponseEntity<Void> updateEducation(@RequestBody @Valid EducationUpdateDto educationUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		educationService.updateEducation(id, educationUpdateDto);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteEducation(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		educationService.deleteEducation(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
