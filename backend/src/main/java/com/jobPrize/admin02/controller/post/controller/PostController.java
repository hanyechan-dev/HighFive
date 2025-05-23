package com.jobPrize.admin02.controller.post.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.post.PostCreateDto;
import com.jobPrize.dto.common.post.PostResponseDto;
import com.jobPrize.dto.common.post.PostSummaryDto;
import com.jobPrize.dto.common.post.PostUpdateDto;
import com.jobPrize.service.common.post.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
	
    
    @PostMapping("/create")
	public String createPost(@RequestParam Long id, 
			@RequestBody @Valid PostCreateDto dto) {
		postService.createPost(id, dto);
		return "게시글이 등록되었습니다.";
	}
    
    @PutMapping("/update/{id}")
    public String updatePost(@PathVariable Long id,
    		@RequestBody @Valid PostUpdateDto dto) {
    	postService.updatePost(id, dto);
    	return "게시글이 수정되었습니다.";
    }
    
    @GetMapping("/page")
    public Page<PostSummaryDto> readPostPage(Pageable pageable){
    	return postService.readPostPage(pageable);
    }
    
    @GetMapping("/detail/{postId}")
    public ResponseEntity<PostResponseDto> readPostDetail(@PathVariable Long postId) {
        PostResponseDto dto = postService.readPost(postId);
        return ResponseEntity.ok(dto);
    }

    
    
    
	

}
