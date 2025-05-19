package com.jobPrize.Admin02.service.dto.post;

import com.jobPrize.entity.common.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDto {
	
	private Long id;
	private String title;
	private String content;
	
	
	public static PostCreateDto from(Post post) {
		return PostCreateDto 
				.builder()
				.id(post.getUser().getId())
				.title(post.getTitle())
				.content(post.getContent())
				.build();
	}

}
