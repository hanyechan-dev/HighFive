package com.jobPrize.repository.admin.feedbackPrompt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.admin.FeedbackPrompt;

public interface FeedbackPromptRepository extends JpaRepository<FeedbackPrompt, Long>, FeedbackPromptRepositoryCustom {

}
