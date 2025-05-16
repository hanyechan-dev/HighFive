package com.jobPrize.Admin02.service.dto;

import java.time.LocalDateTime;

import com.jobPrize.entity.common.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostListResponseDto {
	private Long authorId;
	private String title;
	private String content;
	private int commentCount;
	private LocalDateTime createdDate;
	
	public static PostListResponseDto from(Post post) {
		return PostListResponseDto
				.builder()
				.authorId(post.getUser().getId())
				.title(post.getTitle())
				.content(post.getContent())
				.commentCount(post.getComments().size())
				.createdDate(post.getCreatedTime())
				.build();
	}

}
