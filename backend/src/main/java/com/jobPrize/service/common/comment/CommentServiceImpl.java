package com.jobPrize.service.common.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;
import com.jobPrize.repository.common.comment.CommentRepository;
import com.jobPrize.repository.common.post.PostRepository;
import com.jobPrize.repository.common.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	@Override
	public void createComment(Long id, CommentCreateDto dto) {

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		Post post = postRepository.findById(dto.getPostId())
				.orElseThrow(() -> new CustomEntityNotFoundException("게시글"));

		Comment comment = Comment.createOf(post, user, dto.getContent());

		commentRepository.save(comment);
	}


}
