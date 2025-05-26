package com.jobPrize.service.common.comment;

import com.jobPrize.dto.common.comment.CommentCreateDto;

public interface CommentService {

	void createComment(Long id, CommentCreateDto dto);

}
