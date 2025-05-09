package com.jobPrize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback_prompt")		//피드백 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FeedbackPrompt {
	
	@Id
	@Column(name = "feedback_prompt_id", nullable = false)
	private String feedbackPromptId;		//피드백 프롬프트 아이디
	
	@Column(nullable = false)
	private String title;		//피드백 프롬프트 제목
	
	@Column(nullable = false)
	private String content;		//피드백 프롬프트 내용
	
	@Column(name = "is_applied", nullable=false)
	private Boolean isApplied;			//적용중 여부


}
