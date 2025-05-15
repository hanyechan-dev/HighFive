package com.jobPrize.repository.admin.feedbackPrompt;

import java.util.Optional;

import com.jobPrize.entity.admin.FeedbackPrompt;
import com.jobPrize.entity.admin.QFeedbackPrompt;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedbackPromptRepositoryImpl implements FeedbackPromptRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<FeedbackPrompt> findAppliedPrompt(){
		QFeedbackPrompt prompt = QFeedbackPrompt.feedbackPrompt;
		
		FeedbackPrompt result = queryFactory
				.selectFrom(prompt)
				.where(prompt.isApplied.isTrue())		//isApplied = true
				.fetchOne();
		
		return Optional.ofNullable(result);
	}


}
