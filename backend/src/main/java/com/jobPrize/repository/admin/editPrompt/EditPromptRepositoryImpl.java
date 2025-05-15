package com.jobPrize.repository.admin.editPrompt;

import java.util.Optional;

import com.jobPrize.entity.admin.EditPrompt;
import com.jobPrize.entity.admin.QEditPrompt;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditPromptRepositoryImpl implements EditPromptRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<EditPrompt> findAppliedPrompt(){
		QEditPrompt prompt = QEditPrompt.editPrompt;
		
		EditPrompt result = queryFactory
				.selectFrom(prompt)
				.where(prompt.isApplied.isTrue())		//isApplied = true
				.fetchOne();		
		
		return Optional.ofNullable(result);
		
				
	}

}
