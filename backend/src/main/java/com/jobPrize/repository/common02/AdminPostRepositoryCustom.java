package com.jobPrize.repository.common02;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Post;

public interface AdminPostRepositoryCustom {
	Page<Post> findAllWithComments(Pageable pageable); 

}
