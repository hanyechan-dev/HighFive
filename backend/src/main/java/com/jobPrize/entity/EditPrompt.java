package com.jobPrize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EDIT_PROMPT_ID", nullable = false)
	private String id;		//첨삭 프롬프트 아이디
	
	@Column(nullable = false)
	private String title;		//첨삭 프롬프트 제목
	
	@Column(nullable = false)
	private String content;			//첨삭 프롬프트 내용
	
	@Column(name = "IS_APPLIED", nullable = false)
	private boolean isApplied;			//적용중 여부

	public void updateEditPrompt(String title, String content) {
		this.title = title;
		this.content = content;				//첨삭 프롬르트의 제목, 내용을 업데이트 처리하는 메서드
	}
	
	public void apply() {
		isApplied=true;				//프롬프트를 적용 상태를 설정하는 메서드
	}
	
	public void unApply() {
		isApplied=false;			//프롬프트를 미적용 상태를 설정하는 메서드
	}
	
	
}

