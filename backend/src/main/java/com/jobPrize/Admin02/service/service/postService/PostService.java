package com.jobPrize.Admin02.service.service.postService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.Admin02.service.dto.post.PostContentResponseDto;
import com.jobPrize.Admin02.service.dto.post.PostCreateDto;
import com.jobPrize.Admin02.service.dto.post.PostSummaryResponseDto;
import com.jobPrize.Admin02.service.dto.post.PostUpdateDto;

public interface PostService {

	void postCreate(PostCreateDto dto, String token);
	
	void postUpdate(PostUpdateDto dto, String token);
		
	 Page<PostSummaryResponseDto> getAllPosts(Pageable pageable);
	
	PostContentResponseDto getPostById(Long postId);
}
