package com.jobPrize.controller.member.education;

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
import com.jobPrize.dto.member.education.EducationCreateDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.education.EducationUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.member.education.EducationService;
import com.jobPrize.util.SecurityUtil;

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
	public ResponseEntity<Void> createEducation(@RequestBody EducationCreateDto educationCreateDto) {
		
		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();
		
		educationService.createEducation(id, userType, educationCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<Void> updateEducation(@RequestBody EducationUpdateDto educationUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		educationService.updateEducation(id, educationUpdateDto);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteEducation(@RequestBody DeleteIdDto deleteIdDto) {
		
		Long id = SecurityUtil.getId();
		
		educationService.deleteEducation(id, deleteIdDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
