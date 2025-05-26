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
<<<<<<< HEAD
	public List<FeedbackPromptResponseDto> readAllList(UserType userType) {

		assertUtil.assertUserType(userType, UserType.관리자, "조회");

=======
	public List<FeedbackPromptSummaryDto> readAllList() {
>>>>>>> origin/ADMIN02_CONTROLLER
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
<<<<<<< HEAD
	public FeedbackPromptResponseDto readFeedbackPrompt(UserType userType, Long feedbackPromptId) {

		assertUtil.assertUserType(userType, UserType.관리자, "조회");
		
		FeedbackPrompt prompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));
=======
	public FeedbackPromptResponseDto readFeedbackPromptById(Long feedbackPromptId) {
		FeedbackPrompt prompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));
>>>>>>> origin/ADMIN02_CONTROLLER
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
	@Override
	@Transactional(readOnly = true)
	public FeedbackPromptResponseDto readAppliedFeedbackPrompt(){
		 FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findAppliedPrompt()
			        .orElseThrow(() -> new EntityNotFoundException("현재 '적용중'인 프롬프트가 존재하지 않습니다"));

			    return FeedbackPromptResponseDto.from(feedbackPrompt);

	}

	@Override
	public void deleteFeedbackPrompt(Long feedbackPromptId) {
		
		feedbackPromptRepository.deleteById(feedbackPromptId);

	}
	
}