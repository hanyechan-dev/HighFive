package com.jobPrize.service.common.comment;

import java.util.List;

import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.dto.common.comment.CommentResponseDto;

public interface CommentService {

	void createComment(Long id, CommentCreateDto dto);
	
	List<CommentResponseDto> readCommentsByPostIdList(Long postId);

}
