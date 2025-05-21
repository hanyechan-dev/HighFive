package com.jobPrize.service.admin.feedbackPrompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.admin.FeedbackPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackPromptServiceImpl implements FeedbackPromptService {
	private final FeedbackPromptRepository feedbackPromptRepository;

	@Override
	public void createFeedbackPrompt(UserType userType, FeedbackPromptCreateDto dto) {
	if(userType != UserType.관리자) {
		throw new AccessDeniedException("관리자만 작성할 수 있습니다.");
	}
	FeedbackPrompt prompt = FeedbackPrompt.createFrom(dto);
	feedbackPromptRepository.save(prompt);
}

	@Override
	public void updateFeedbackPrompt(UserType userType, FeedbackPromptUpdateDto dto) {
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));
		if (userType != UserType.관리자) {
			throw new AccessDeniedException("관리자만 작성할 수 있습니다.");
		}
		feedbackPrompt.updateFeedbackPrompt(dto.getTitle(), dto.getContent());

	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackPromptResponseDto> readAllList() {
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
	public FeedbackPromptResponseDto readFeedbackPromptById(FeedbackPromptResponseDto dto) {
		FeedbackPrompt prompt = feedbackPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));
		return FeedbackPromptResponseDto.from(prompt);
	}

	@Override
	public void applyFeedbackPrompt(Long feedbackPromptId) {
		unApplyFeedbackPrompt();
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));
		feedbackPrompt.apply();
	}

	@Override
	public void unApplyFeedbackPrompt() {
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findAppliedPrompt()
				.orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));
		feedbackPrompt.unApply();
		
	}

}