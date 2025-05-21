package com.jobPrize.Admin02.service.service.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.Admin02.service.dto.comment.CommentCreateDto;
import com.jobPrize.Admin02.service.dto.comment.CommentResponseDto;
import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.comment.CommentRepository;
import com.jobPrize.repository.common.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void createComment(Long id, CommentCreateDto dto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));
		Post post = postRepository.findById(dto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

		Comment comment = Comment.createOf(post, user, dto.getContent());

		commentRepository.save(comment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentResponseDto> readCommentsByPostIdList(Long postId) {
		Post post = postRepository.findWithCommentsByPostId(postId)
				.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않거나 댓글이 없습니다."));

		List<CommentResponseDto> result = new ArrayList<>();

		for (Comment comment : post.getComments()) {
			result.add(CommentResponseDto.from(comment));
		}

		return result;
	}

}
