package com.jobPrize.dto.common.post;

import java.time.LocalDateTime;
import java.util.List;

import com.jobPrize.dto.common.comment.CommentResponseDto;
import com.jobPrize.entity.common.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {
	
	private Long id;
	private Long author_id;
	private String nicknameOrName;
	private String title;
	private String content;
	private List<CommentResponseDto> comments;
	private LocalDateTime createdDate;
	
	
	public static PostResponseDto of(Post post, String nicknameOrName, List<CommentResponseDto> comments) {

		return PostResponseDto
				.builder()
				.id(post.getId())
				.author_id(post.getUser().getId())
				.nicknameOrName(nicknameOrName)
				.title(post.getTitle())
				.content(post.getContent())
				.comments(comments)
				.createdDate(post.getCreatedTime())
				.build();
	}
	

}
