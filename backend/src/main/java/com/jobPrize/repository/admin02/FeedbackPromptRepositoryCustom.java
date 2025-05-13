package com.jobPrize.repository.admin02;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.admin.FeedbackPrompt;

public interface FeedbackPromptRepositoryCustom {
	Optional<FeedbackPrompt> findAppliedPrompt();
	List<FeedbackPrompt> findAll(); 
}
