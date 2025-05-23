package com.jobPrize.admin02.controller.comment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.dto.common.comment.CommentResponseDto;
import com.jobPrize.service.common.comment.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post/comment")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@PutMapping("/create/comment/{id}")
	public String createComment(@PathVariable Long id, @RequestBody CommentCreateDto dto) {
		commentService.createComment(id, dto);
		return "댓글이 작성 되었습니다.";
	}
	
	@GetMapping("/post/list/{postId}")
	public List<CommentResponseDto> readComments(@PathVariable Long postId){
		return commentService.readCommentsByPostIdList(postId);
		
	}


}
