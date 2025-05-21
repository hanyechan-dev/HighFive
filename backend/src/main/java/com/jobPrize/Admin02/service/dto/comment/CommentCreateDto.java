package com.jobPrize.Admin02.service.dto.comment;

import com.jobPrize.entity.common.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDto {
	
	private String authorNickname;
	
	private Long postId;
	
	@NotBlank(message = "댓글은 필수로 입력해야 합니다.")
	@Size(max = 50, message = "댓글은 50자 이하로 입력해야 합니다.")
	private String content;
	
	
	public static CommentResponseDto from(Comment comment) {
		return CommentResponseDto
				.builder()
				.id(comment.getPost().getId())
				.authorNickname(comment.getPost().getUser().getMember().getNickname())
				.content(comment.getContent())
				.createdDate(comment.getCreatedTime())
				.build();
	}
}
