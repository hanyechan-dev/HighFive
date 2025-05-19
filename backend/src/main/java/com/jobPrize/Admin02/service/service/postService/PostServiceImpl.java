package com.jobPrize.Admin02.service.service.postService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.Admin02.service.dto.post.PostContentResponseDto;
import com.jobPrize.Admin02.service.dto.post.PostCreateDto;
import com.jobPrize.Admin02.service.dto.post.PostSummaryResponseDto;
import com.jobPrize.Admin02.service.dto.post.PostUpdateDto;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final TokenProvider tokenProvider;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	@Override
	public void postCreate(PostCreateDto dto, String token) {
		Long userId = Long.parseLong(tokenProvider.getIdFromToken(token));
		 User user = userRepository.findByIdAndDeletedDateIsNull(userId)
		            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));
		
		Post post = Post
				.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.user(user)
				.build();
		postRepository.save(post);
	}

	@Override
	public void postUpdate(PostUpdateDto dto, String token) {
		Long userId = Long.parseLong(tokenProvider.getIdFromToken(token));
		Post post = postRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalArgumentException("수정할 게시글이 없습니다."));
		if(!post.getUser().getId().equals(userId)) {
			throw new IllegalArgumentException("본인의 게시글만 수정할 수 있습니다.");
		}
		post.updatePost(dto.getTitle(), dto.getContent());

		postRepository.save(post);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostSummaryResponseDto> getAllPosts(Pageable pageable) {
	    Page<Post> postPage = postRepository.findAll(pageable);

	    return postPage.map(PostSummaryResponseDto::from);
	}

	
	@Override
	public PostContentResponseDto getPostById(Long postId) {
	    Post post = postRepository.findById(postId)
	        .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

	    return PostContentResponseDto.from(post);
	}

}
