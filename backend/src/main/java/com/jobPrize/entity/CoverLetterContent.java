package com.jobPrize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cover_letter_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoverLetterContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COVER_LETTER_CONTENT_ID")
    private Long coverLetterContentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COVER_LETTER_ID")
	private CoverLetter coverLetter;
	
	@Column(name="ITEM", nullable = false)
	private String item;
	
	@Column(name="CONTENT", nullable = false)
	private String content;
	
	public void updateContent(String item, String content) {
		this.item = item;
		this.content = content;
	}

}