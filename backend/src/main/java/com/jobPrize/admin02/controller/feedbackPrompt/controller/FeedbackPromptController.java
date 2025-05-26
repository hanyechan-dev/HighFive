package com.jobPrize.admin02.controller.feedbackPrompt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptSummaryDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.feedbackPrompt.FeedbackPromptService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/feedback-prompt")
@RequiredArgsConstructor
public class FeedbackPromptController {
	
	private final FeedbackPromptService feedbackPromptService;

	
	@PostMapping("/create")		
	public ResponseEntity<Void> createFeedbackPrompt(@RequestBody @Valid FeedbackPromptCreateDto dto) {
		UserType userType = SecurityUtil.getUserType();
		feedbackPromptService.createFeedbackPrompt(userType, dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}
	
	@PutMapping("/update")		
	public ResponseEntity<Void> updateFeedbackPrompt(@RequestBody @Valid FeedbackPromptUpdateDto dto) {
		UserType userType = SecurityUtil.getUserType();
		feedbackPromptService.updateFeedbackPrompt(userType,dto);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
	
	@GetMapping("/setting")
	public ResponseEntity<Map<String, Object>> readAppliedFeedbackPromptAndList() {
		FeedbackPromptResponseDto feedbackPromptResponseDto = feedbackPromptService.readAppliedFeedbackPrompt();
		List<FeedbackPromptSummaryDto> feedbackPromptSummaryDtos =feedbackPromptService.readAllList();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("feedbackPromptResponseDto",feedbackPromptResponseDto);
		map.put("feedbackPromptSummaryDtos",feedbackPromptSummaryDtos);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(map);
	}
	
	@PutMapping("/apply/{feedbackPromptId}")
	public ResponseEntity<Void> applyFeedbackPrompt(@PathVariable Long feedbackPromptId) {
		feedbackPromptService.applyFeedbackPrompt(feedbackPromptId);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
			
	}
}
