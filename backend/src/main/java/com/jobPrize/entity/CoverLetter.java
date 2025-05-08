package com.jobPrize.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cover_letter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoverLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COVER_LETTER_ID")
    private Long coverLetterId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Member member;
	
	@Column(name="TITLE", nullable = false)
	private String title;
	
	@Column(name="WRITE_DATE", nullable = false)
	private LocalDateTime writeDate;
	
	@OneToMany(mappedBy = "coverLetter")
	private List<CoverLetterContent> coverLetterContents;
		

}
