package com.jobPrize.Admin02.service.dto.post;

import java.time.LocalDateTime;

import com.jobPrize.entity.common.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostSummaryResponseDto {
	private Long authorId;
	private String title;
	private String content;
	private int commentCount;
	private LocalDateTime createdDate;
	
	public static PostSummaryResponseDto from(Post post) {
		return PostSummaryResponseDto
				.builder()
				.authorId(post.getUser().getId())
				.title(post.getTitle())
				.content(post.getContent())
				.commentCount(post.getComments().size())
				.createdDate(post.getCreatedTime())
				.build();
	}

}
