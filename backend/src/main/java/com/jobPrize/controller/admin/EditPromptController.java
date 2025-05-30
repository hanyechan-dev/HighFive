package com.jobPrize.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.editPrompt.EditPromptCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.dto.admin.setting.EditPromptSettingResponseDto;
import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.admin.editPrompt.EditPromptService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/edit-prompts")
@RequiredArgsConstructor
public class EditPromptController {

	private final EditPromptService editPromptService;
	
	@GetMapping
	public ResponseEntity<EditPromptSettingResponseDto> readEditPromptSetting(){
		
		UserType userType = SecurityUtil.getUserType();
		
		EditPromptResponseDto applied = editPromptService.readAppliedEditPrompt(userType);
		
		List<EditPromptSummaryDto> list = editPromptService.readAllList(userType);
		
		EditPromptSettingResponseDto editPromptSettingResponseDto = EditPromptSettingResponseDto.builder()
				.list(list)
				.applied(applied)
				.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(editPromptSettingResponseDto);
	}
	
	@PostMapping	
	public ResponseEntity<Void> createEditPrompt(@RequestBody @Valid EditPromptCreateDto dto) {
		
		UserType userType = SecurityUtil.getUserType();
		
		editPromptService.createEditPrompt(userType, dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping	
	public ResponseEntity<Void> updateEditPrompt(@RequestBody @Valid EditPromptUpdateDto dto) {
		
		UserType userType = SecurityUtil.getUserType(); 
		
		editPromptService.updateEditPrompt(userType, dto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/application")	
	public ResponseEntity<Void> applyEditPrompt(@RequestBody @Valid IdDto editPromptId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		editPromptService.applyEditPrompt(userType, editPromptId.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping("/deletion")
	public ResponseEntity<Void> deleteEditPrompt(@RequestBody @Valid IdDto editPromptId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		editPromptService.deleteEditPrompt(userType, editPromptId.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
