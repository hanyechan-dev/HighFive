package com.jobPrize.Admin02.service.dto;

import com.jobPrize.entity.common.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
	
	private Long id;
	private String title;
	private String content;
	
	
	public static PostRequestDto from(Post post) {
		return PostRequestDto 
				.builder()
				.id(post.getUser().getAdmin().getId())
				.title(post.getTitle())
				.content(post.getContent())
				.build();
	}

}
