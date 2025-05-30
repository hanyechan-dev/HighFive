package com.jobPrize.repository.common.post;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Post;

public interface PostRepositoryCustom {
	Map<String,Object> findAllWithCommentsCount(Pageable pageable); 
	Optional<Post> findWithCommentsByPostId(Long id);
	Optional<Long> findUserIdByPostId(Long id);


}
