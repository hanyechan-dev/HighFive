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
import com.jobPrize.dto.member.languageTest.LanguageTestCreateDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.member.languageTest.LanguageTestService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/language-tests")
@RequiredArgsConstructor
public class LanguageTestController {
	
	private final LanguageTestService languageTestService;
	
	@GetMapping
	public ResponseEntity<List<LanguageTestResponseDto>> readMyLanguageTests() {
		
		Long id = SecurityUtil.getId();
		
		List<LanguageTestResponseDto> languageTestResponseDtos = languageTestService.readLanguageTestList(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(languageTestResponseDtos);
	}

	@PostMapping
	public ResponseEntity<LanguageTestResponseDto> createLanguageTest(@RequestBody @Valid LanguageTestCreateDto languageTestCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		LanguageTestResponseDto languageTestResponseDto = languageTestService.createLanguageTest(id, userType, languageTestCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(languageTestResponseDto);
	}

	@PutMapping
	public ResponseEntity<LanguageTestResponseDto> updateMyLanguageTest(@RequestBody @Valid LanguageTestUpdateDto languageTestUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		LanguageTestResponseDto languageTestResponseDto = languageTestService.updateLanguageTest(id, languageTestUpdateDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(languageTestResponseDto);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMyLanguageTest(@RequestBody @Valid IdDto idDto) {
		
		Long id = SecurityUtil.getId();
		
		languageTestService.deleteLanguageTest(id, idDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
