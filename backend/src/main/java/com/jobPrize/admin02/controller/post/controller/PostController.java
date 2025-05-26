package com.jobPrize.admin02.controller.post.controller;


import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Void> createPost( @RequestBody @Valid PostCreateDto dto) {
    	Long id = SecurityUtil.getId();
		postService.createPost(id, dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
		
	}
    
    @PutMapping("/update")
    public ResponseEntity<Void> updatePost(@RequestBody @Valid PostUpdateDto dto) {
    	Long id = SecurityUtil.getId();
    	postService.updatePost(id, dto);
    	
    	return ResponseEntity
    			.status(HttpStatus.NO_CONTENT)
    			.build();
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<PostSummaryDto>> readPostPage(Pageable pageable){
    	Page<PostSummaryDto> page = postService.readPostPage(pageable);

    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.body(page);
    }
    
    @GetMapping("/detail/{postId}")
    public ResponseEntity<PostResponseDto> readPostDetail(@PathVariable Long postId) {
        PostResponseDto dto = postService.readPost(postId);
        
       return ResponseEntity
    		   .status(HttpStatus.OK)
    		   .body(dto);
       
    }

    
    
    
	

}
