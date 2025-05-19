package com.jobPrize.Admin02.service.dto.comment;

import com.jobPrize.entity.common.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDto {
	private Long userId;
	private Long postId;
	private String content;
	
	
	public static CommentResponseDto from(Comment comment) {
		return CommentResponseDto
				.builder()
				.id(comment.getPost().getId())
				.author(comment.getPost().getUser().getId())
				.content(comment.getContent())
				.createdDate(comment.getCreatedTime())
				.build();
	}
}
