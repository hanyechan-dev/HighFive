package com.jobPrize.controller.admin.editPrompt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.editPrompt.EditPromptCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.editPrompt.EditPromptService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/edit-prompt")
@RequiredArgsConstructor
public class EditPromptController {

	private final EditPromptService editPromptService;
	
	@GetMapping("/setting")	
	public ResponseEntity<Map<String, Object>> readAppliedEditPromptAndList() {
		
		UserType userType = SecurityUtil.getUserType();
		
		EditPromptResponseDto editPromptResponseDto = editPromptService.readAppliedEditPrompt(userType);
		
		List<EditPromptSummaryDto> editPromptSummaryDto = editPromptService.readAllList(userType);
		
		Map<String, Object> map = new HashMap<>();
		map.put("EditPromptResponseDto", editPromptResponseDto);
		map.put("EditPromptSummaryDto", editPromptSummaryDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}	

	@PostMapping("/create")		
	public ResponseEntity<Void> createEditPrompt(@RequestBody @Valid EditPromptCreateDto dto) {
		
		UserType userType = SecurityUtil.getUserType();
		
		editPromptService.createEditPrompt(userType, dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/update")		
	public ResponseEntity<Void> updateEditPrompt(@RequestBody @Valid EditPromptUpdateDto dto) {
		
		UserType userType = SecurityUtil.getUserType(); 
		
		editPromptService.updateEditPrompt(userType, dto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/apply/{editPromptId}")	
	public ResponseEntity<Void> applyEditPrompt(@PathVariable Long editPromptId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		editPromptService.applyEditPrompt(userType, editPromptId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteEditPrompt(@RequestBody Long editPromptId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		editPromptService.deleteEditPrompt(userType, editPromptId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
