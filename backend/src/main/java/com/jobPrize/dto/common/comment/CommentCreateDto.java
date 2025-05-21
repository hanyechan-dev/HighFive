package com.jobPrize.dto.common.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateDto {
	
	@NotNull(message = "댓글 작성 시, 게시글 id는 필수 입니다.")
	private Long postId;
	
	@NotBlank(message = "댓글은 필수로 입력해야 합니다.")
	@Size(max = 50, message = "댓글은 50자 이하로 입력해야 합니다.")
	private String content;

}
