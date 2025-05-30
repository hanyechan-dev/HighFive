package com.jobPrize.entity.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.dto.common.post.PostCreateDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post") // 게시글 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID", nullable = false)
	private Long id; // 게시글 아이디

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID", nullable = false) // 작성자
	private User user;

	@Column(nullable = false)
	private String title; // 게시글 제목

	@Column(nullable = false)
	private String content; // 게시글 내용

	@CreatedDate
	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime; // 작성 시간

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	public void updatePost(String title, String content) {
		this.title = title;
		this.content = content; // 게시글 제목, 게시글 내용을 업데이트 처리하는 메서드
	}

	public static Post of(User user, PostCreateDto dto) {
	    return Post.builder()
	        .title(dto.getTitle())
	        .content(dto.getContent())
	        .user(user)
	        .build();
	}

}
