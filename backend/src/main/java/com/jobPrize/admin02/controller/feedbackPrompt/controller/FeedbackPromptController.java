package com.jobPrize.admin02.controller.feedbackPrompt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private FeedbackPromptService feedbackPromptService;

	
	@PostMapping("/create")		
	public String createFeedbackPrompt(@RequestParam UserType userType,
			@RequestBody @Valid FeedbackPromptCreateDto dto) {
		feedbackPromptService.createFeedbackPrompt(userType, dto);
		return "피드백 프롬프트가 등록되었습니다.";
	}
	
	@PutMapping("/update")		
	public String updateFeedbackPrompt(@RequestParam UserType userType,
			@RequestBody @Valid FeedbackPromptUpdateDto dto) {
		feedbackPromptService.updateFeedbackPrompt(userType,dto);
		return "피드백 프롬프트가 수정되었습니다.";
	}
	
	@GetMapping("/setting")
	public Map<String, Object> readApplyedFeedbackPromptAndList() {
		FeedbackPromptResponseDto feedbackPromptResponseDto = feedbackPromptService.readApplyedFeedbackPrompt();
		List<FeedbackPromptSummaryDto> feedbackPromptSummaryDtos =feedbackPromptService.readAllList();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("feedbackPromptResponseDto",feedbackPromptResponseDto);
		map.put("feedbackPromptSummaryDtos",feedbackPromptSummaryDtos);
		
		return map;
	}
	

	
	@GetMapping("/list/{id}")
	public FeedbackPromptResponseDto getFeedbackPromptBuId(@PathVariable Long feedbackPromptId) {
		return feedbackPromptService.readFeedbackPromptById(feedbackPromptId);
	}
	
	@PutMapping("/apply{id}")
	public String applyFeedbackPrompt(@PathVariable Long feedbackPromptId) {
		feedbackPromptService.applyFeedbackPrompt(feedbackPromptId);
		return "프롬프트가 적용으로 변경되었습니다.";
	}
	
	@PutMapping("unapply")
	public String unApplyFeedbackPrompt() {
		feedbackPromptService.unApplyFeedbackPrompt();
		return "프롭프트 적용이 취소되었습니다.";
	}
	
}
