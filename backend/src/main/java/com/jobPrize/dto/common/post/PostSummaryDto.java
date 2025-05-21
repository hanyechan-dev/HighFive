package com.jobPrize.dto.common.post;

import java.time.LocalDateTime;

import com.jobPrize.entity.common.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostSummaryDto {
	private Long id;
	private String nicknameOrName;
	private String title;
	private int commentCount;
	private LocalDateTime createdDate;
	
	public static PostSummaryDto of(Post post, String nicknameOrName) {
		return PostSummaryDto
				.builder()
				.id(post.getId())
				.nicknameOrName(nicknameOrName)
				.title(post.getTitle())
				.commentCount(post.getComments().size())
				.createdDate(post.getCreatedTime())
				.build();
	}

}
