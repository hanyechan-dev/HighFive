package com.jobPrize.service.common.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;
import com.jobPrize.repository.common.comment.CommentRepository;
import com.jobPrize.repository.common.post.PostRepository;
import com.jobPrize.repository.common.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
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
				.orElseThrow(() -> new EntityNotFoundException("유효하지 않은 사용자입니다."));
		Post post = postRepository.findById(dto.getPostId())
				.orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다."));

		Comment comment = Comment.createOf(post, user, dto.getContent());

		commentRepository.save(comment);
	}


}
