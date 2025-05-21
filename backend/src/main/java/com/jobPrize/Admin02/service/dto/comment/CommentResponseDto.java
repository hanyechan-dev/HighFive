package com.jobPrize.Admin02.service.dto.comment;

import java.time.LocalDateTime;

import com.jobPrize.entity.common.Comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {
	private Long id;
	private String authorNickname;
	private String content;
	private LocalDateTime createdDate;
	
	public static CommentResponseDto from(Comment comment) {
		return CommentResponseDto
				.builder()
				.id(comment.getPost().getId())
				.authorNickname(comment.getUser().getMember().getNickname())
				.content(comment.getContent())
				.createdDate(comment.getCreatedTime())
				.build();
	}

}
