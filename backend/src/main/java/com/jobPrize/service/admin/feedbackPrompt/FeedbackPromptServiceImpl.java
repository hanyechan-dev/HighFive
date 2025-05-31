package com.jobPrize.service.admin.feedbackPrompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptSummaryDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.admin.FeedbackPrompt;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackPromptServiceImpl implements FeedbackPromptService {
	
	private final FeedbackPromptRepository feedbackPromptRepository;
	
	private final AssertUtil assertUtil;

	private final String ENTITY_NAME = "피드백 프롬프트";

	private final static UserType ALLOWED_USER_TYPE = UserType.관리자;

	@Override
	public void createFeedbackPrompt(UserType userType, FeedbackPromptCreateDto dto) {
		
		String action = "작성";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
	
		FeedbackPrompt prompt = FeedbackPrompt.createFrom(dto);
	
		feedbackPromptRepository.save(prompt);
	}

	@Override
	public void updateFeedbackPrompt(UserType userType, FeedbackPromptUpdateDto dto) {
		
		String action = "수정";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		feedbackPrompt.updateFeedbackPrompt(dto.getTitle(), dto.getContent());

	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackPromptSummaryDto> readAllList(UserType userType) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		List<FeedbackPrompt> feedbackPrompts = feedbackPromptRepository.findAll();
		List<FeedbackPromptSummaryDto> results = new ArrayList<>();

		for (FeedbackPrompt feedbackPrompt : feedbackPrompts) {
			FeedbackPromptSummaryDto dto = FeedbackPromptSummaryDto.from(feedbackPrompt);
			results.add(dto);
		}
		return results;
	}

	@Override
	@Transactional(readOnly = true)
	public FeedbackPromptResponseDto readFeedbackPrompt(UserType userType, Long feedbackPromptId) {

		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		FeedbackPrompt prompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		return FeedbackPromptResponseDto.from(prompt);
	}

	@Override
	public void applyFeedbackPrompt(UserType userType, Long feedbackPromptId) {
		
		String action = "적용";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		unApplyFeedbackPrompt();
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		feedbackPrompt.apply();
	}

	@Override
	@Transactional(readOnly = true)
	public FeedbackPromptResponseDto readAppliedFeedbackPrompt(UserType userType){
		
		String action = "조회";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findAppliedPrompt()
				 .orElseThrow(() -> new CustomEntityNotFoundException("적용된 " + ENTITY_NAME));

		 return FeedbackPromptResponseDto.from(feedbackPrompt);

	}

	@Override
	public void deleteFeedbackPrompt(UserType userType, Long feedbackPromptId) {
		
		String action = "삭제";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		feedbackPromptRepository.deleteById(feedbackPromptId);

	}
	
	
	

	private void unApplyFeedbackPrompt() {
		feedbackPromptRepository.findAppliedPrompt()
			.ifPresent(feedbackPrompt -> feedbackPrompt.unApply());
		
	}
	
}