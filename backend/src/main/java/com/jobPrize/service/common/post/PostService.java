package com.jobPrize.service.common.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.common.post.PostCreateDto;
import com.jobPrize.dto.common.post.PostResponseDto;
import com.jobPrize.dto.common.post.PostSummaryDto;
import com.jobPrize.dto.common.post.PostUpdateDto;

public interface PostService {

	void createPost(Long id, PostCreateDto dto);
	
	void updatePost(Long id, PostUpdateDto dto);
		
	Page<PostSummaryDto> readPostPage(Pageable pageable);
	
	PostResponseDto readPost(Long postId);
}
