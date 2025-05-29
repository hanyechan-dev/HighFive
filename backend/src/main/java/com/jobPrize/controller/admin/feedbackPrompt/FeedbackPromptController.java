package com.jobPrize.controller.admin.feedbackPrompt;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptSummaryDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.dto.admin.setting.FeedbackPromptSettingResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.admin.feedbackPrompt.FeedbackPromptService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feedback-prompts")
@RequiredArgsConstructor
public class FeedbackPromptController {
	
	private final FeedbackPromptService feedbackPromptService;
	
	@GetMapping
public ResponseEntity<FeedbackPromptSettingResponseDto> readFeedbackPromptSetting(){
		
		UserType userType = SecurityUtil.getUserType();
		
		FeedbackPromptResponseDto applied = feedbackPromptService.readAppliedFeedbackPrompt(userType);
		
		List<FeedbackPromptSummaryDto> list =feedbackPromptService.readAllList(userType);
		
		FeedbackPromptSettingResponseDto feedbackPromptSettingResponseDto = FeedbackPromptSettingResponseDto.builder()
				.list(list)
				.applied(applied)
				.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(feedbackPromptSettingResponseDto);
	}

	
	@PostMapping	
	public ResponseEntity<Void> createFeedbackPrompt(@RequestBody @Valid FeedbackPromptCreateDto dto) {
		
		UserType userType = SecurityUtil.getUserType();
		
		feedbackPromptService.createFeedbackPrompt(userType, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping		
	public ResponseEntity<Void> updateFeedbackPrompt (@RequestBody @Valid FeedbackPromptUpdateDto dto) {
		
		UserType userType = SecurityUtil.getUserType();
		
		feedbackPromptService.updateFeedbackPrompt(userType,dto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

	
	@PutMapping("/application")
	public ResponseEntity<Void> applyFeedbackPrompt(@RequestBody Long feedbackPromptId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		feedbackPromptService.applyFeedbackPrompt(userType, feedbackPromptId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
	}
	
	@PostMapping("/deletion")
	public ResponseEntity<Void> deleteFeedbackPrompt(@RequestBody Long feedbackPromptId) {
		
		UserType userType = SecurityUtil.getUserType();
		
		feedbackPromptService.deleteFeedbackPrompt(userType, feedbackPromptId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
