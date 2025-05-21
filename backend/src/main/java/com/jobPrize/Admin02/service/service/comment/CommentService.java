package com.jobPrize.Admin02.service.service.comment;

import java.util.List;

import com.jobPrize.Admin02.service.dto.comment.CommentCreateDto;
import com.jobPrize.Admin02.service.dto.comment.CommentResponseDto;

public interface CommentService {

	void createComment(Long id, CommentCreateDto dto);
	
	List<CommentResponseDto> readCommentsByPostIdList(Long postId);

}
