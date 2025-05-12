package com.jobPrize.repository.common02;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Post;

public interface AdminPostRepository extends JpaRepository<Post, Long>, AdminPostRepositoryCustom {

}
