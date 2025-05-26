package com.jobPrize.admin02.controller.comment.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.dto.common.comment.CommentResponseDto;
import com.jobPrize.service.common.comment.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/create")
	public ResponseEntity<Void> createComment(@RequestBody @Valid CommentCreateDto dto) {
		Long id = SecurityUtil.getId();
		commentService.createComment(id, dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}

	@GetMapping("/post/list/{postId}")
	public ResponseEntity<List<CommentResponseDto>> readComments(@PathVariable Long postId) {
		List<CommentResponseDto> comments =commentService.readCommentsByPostIdList(postId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(comments);
	}
}
