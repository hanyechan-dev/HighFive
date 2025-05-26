package com.jobPrize.service.admin.feedbackPrompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.admin.FeedbackPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackPromptServiceImpl implements FeedbackPromptService {
	
	private final FeedbackPromptRepository feedbackPromptRepository;
	
	private final AssertUtil assertUtil;

	@Override
	public void createFeedbackPrompt(UserType userType, FeedbackPromptCreateDto dto) {
		
		assertUtil.assertUserType(userType, UserType.관리자, "작성");
	
		FeedbackPrompt prompt = FeedbackPrompt.createFrom(dto);
	
		feedbackPromptRepository.save(prompt);
	}

	@Override
	public void updateFeedbackPrompt(UserType userType, FeedbackPromptUpdateDto dto) {
		
		assertUtil.assertUserType(userType, UserType.관리자, "수정");
		
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));
		
		feedbackPrompt.updateFeedbackPrompt(dto.getTitle(), dto.getContent());

	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackPromptResponseDto> readAllList(UserType userType) {

		assertUtil.assertUserType(userType, UserType.관리자, "조회");

		List<FeedbackPrompt> feedbackPrompts = feedbackPromptRepository.findAll();
		List<FeedbackPromptResponseDto> results = new ArrayList<>();

		for (FeedbackPrompt feedbackPrompt : feedbackPrompts) {
			FeedbackPromptResponseDto dto = FeedbackPromptResponseDto.from(feedbackPrompt);
			results.add(dto);
		}
		return results;
	}

	@Override
	@Transactional(readOnly = true)
	public FeedbackPromptResponseDto readFeedbackPrompt(UserType userType, Long feedbackPromptId) {

		assertUtil.assertUserType(userType, UserType.관리자, "조회");
		
		FeedbackPrompt prompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));
		return FeedbackPromptResponseDto.from(prompt);
	}

	@Override
	public void applyFeedbackPrompt(UserType userType, Long feedbackPromptId) {
		
		assertUtil.assertUserType(userType, UserType.관리자, "적용");
		
		unApplyFeedbackPrompt();
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));
		feedbackPrompt.apply();
	}

	private void unApplyFeedbackPrompt() {
		feedbackPromptRepository.findAppliedPrompt()
			.ifPresent(feedbackPrompt -> feedbackPrompt.unApply());
		
	}

}