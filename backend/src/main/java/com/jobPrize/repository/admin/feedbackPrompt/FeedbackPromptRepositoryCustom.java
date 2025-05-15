package com.jobPrize.repository.admin.feedbackPrompt;

import java.util.Optional;

import com.jobPrize.entity.admin.FeedbackPrompt;

public interface FeedbackPromptRepositoryCustom {
	Optional<FeedbackPrompt> findAppliedPrompt();

}
