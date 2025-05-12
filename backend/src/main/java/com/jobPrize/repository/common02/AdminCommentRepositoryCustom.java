package com.jobPrize.repository.common02;

import java.util.List;

import com.jobPrize.entity.common.Comment;

public interface AdminCommentRepositoryCustom {
	List<Comment> findCommentsByNickname(String nickname);
	
}
