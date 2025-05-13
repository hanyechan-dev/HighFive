package com.jobPrize.repository.common.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
