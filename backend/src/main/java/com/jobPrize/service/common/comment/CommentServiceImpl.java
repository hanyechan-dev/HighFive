package com.jobPrize.service.common.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.common.comment.CommentCreateDto;
import com.jobPrize.dto.common.comment.CommentResponseDto;
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

	@Override
	@Transactional(readOnly = true)
	public List<CommentResponseDto> readCommentsByPostIdList(Long postId) {
		Post post = postRepository.findWithCommentsByPostId(postId)
				.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

		List<CommentResponseDto> results = new ArrayList<>();

		for (Comment comment : post.getComments()) {
			results.add(CommentResponseDto.of(comment,getNicknameOrNameFromComment(comment)));
		}

		return results;
	}
	
	

	
	
	private String getNicknameOrNameFromComment(Comment comment) {
		String result;
		
		if(comment.getUser().getMember()==null) {
			result = comment.getUser().getName();
	    }
	    else {
	    	result = comment.getUser().getMember().getNickname();
	    }
		
		return result;
	}

}
