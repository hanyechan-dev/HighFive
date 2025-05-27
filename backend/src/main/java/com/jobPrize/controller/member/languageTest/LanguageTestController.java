package com.jobPrize.controller.member.languageTest;

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
import com.jobPrize.dto.member.languageTest.LanguageTestCreateDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.member.languageTest.LanguageTestService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/language-tests")
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
	public ResponseEntity<Void> createLanguageTest(@RequestBody LanguageTestCreateDto languageTestCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		languageTestService.createLanguageTest(id, userType, languageTestCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<Void> updateMyLanguageTest(@RequestBody LanguageTestUpdateDto languageTestUpdateDto) {
		
		Long id = SecurityUtil.getId();
		
		languageTestService.updateLanguageTest(id, languageTestUpdateDto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMyLanguageTest(@RequestBody DeleteIdDto deleteIdDto) {
		
		Long id = SecurityUtil.getId();
		
		languageTestService.deleteLanguageTest(id, deleteIdDto.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
