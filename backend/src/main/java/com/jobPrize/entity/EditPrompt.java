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
@Table(name = "edit_prompt")		//첨삭 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EditPrompt {

	@Id
	@Column(name = "edit_prompt_id", nullable = false)
	private String editPromptId;		//첨삭 프롬프트 아이디
	
	@Column(nullable = false)
	private String title;		//첨삭 프롬프트 제목
	
	@Column(nullable = false)
	private String content;			//첨삭 프롬프트 내용
	
	@Column(name = "is_applied", nullable = false)
	private Boolean isApplied;			//적용중 여부
}
