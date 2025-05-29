package com.jobPrize.controller.common.post;


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

import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.dto.common.post.PostCreateDto;
import com.jobPrize.dto.common.post.PostResponseDto;
import com.jobPrize.dto.common.post.PostSummaryDto;
import com.jobPrize.dto.common.post.PostUpdateDto;
import com.jobPrize.dto.common.read.IdDto;
import com.jobPrize.service.common.comment.CommentService;
import com.jobPrize.service.common.post.PostService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    
    private final CommentService commentService;
    
    @GetMapping
    public ResponseEntity<Page<PostSummaryDto>> readPostPage(Pageable pageable){
    	
    	Page<PostSummaryDto> page = postService.readPostPage(pageable);

    	return ResponseEntity.status(HttpStatus.OK).body(page);
    }
	
    @PostMapping
	public ResponseEntity<Void> createPost(@RequestBody @Valid PostCreateDto dto) {
    	
    	Long id = SecurityUtil.getId();
    	
		postService.createPost(id, dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
    
    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestBody @Valid PostUpdateDto dto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	postService.updatePost(id, dto);
    	
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> readPostDetail(@PathVariable Long id) {
    	
        PostResponseDto dto = postService.readPost(id);
        
       return ResponseEntity.status(HttpStatus.OK).body(dto);
       
    }
    
	@PostMapping("/comments")
	public ResponseEntity<Void> createComment(@RequestBody @Valid CommentCreateDto dto) {
		
		Long id = SecurityUtil.getId();	
		
		commentService.createComment(id, dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
    
    @PostMapping("/deletion")
	public ResponseEntity<Void> deletePost(@RequestBody @Valid IdDto postId) {
    	
    	Long id = SecurityUtil.getId();
		
		postService.deletePost(id, postId.getId());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
