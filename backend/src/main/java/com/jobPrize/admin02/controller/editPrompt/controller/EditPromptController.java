package com.jobPrize.admin02.controller.editPrompt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/edit-prompt")
@RequiredArgsConstructor
public class EditPromptController {

	private final EditPromptService editPromptService;

	@PostMapping("/create")		
	public ResponseEntity<Void> createEditPrompt(@RequestBody @Valid EditPromptCreateDto dto) {
		UserType userType = SecurityUtil.getUserType();
		editPromptService.createEditPrompt(userType, dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}

	@PutMapping("/update")		
	public String updateEditPrompt(@RequestBody @Valid EditPromptUpdateDto dto) {
		UserType userType = SecurityUtil.getUserType(); 
		editPromptService.updateEditPrompt(userType, dto);
		return "첨삭 프롬프트가 수정되었습니다";
	}

	@GetMapping("/setting")	
	public Map<String, Object> readAppliedEditPromptAndList() {
		EditPromptResponseDto editPromptResponseDto = editPromptService.readAppliedEditPrompt();
		List<EditPromptSummaryDto> editPromptSummaryDto = editPromptService.readAllList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("EditPromptResponseDto", editPromptResponseDto);
		map.put("EditPromptSummaryDto", editPromptSummaryDto);
		return map;
	}	
	

	@PutMapping("/apply/{editPromptId}")		
	public String applyEditPrompt(@PathVariable Long editPromptId) {
		editPromptService.applyEditPrompt(editPromptId);
		return "프롬프트가 적용으로 변경되었습니다.";
	}
}
