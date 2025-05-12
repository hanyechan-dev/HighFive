package com.jobPrize.repository.common02;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Comment;

public interface AdminCommentRepository extends JpaRepository<Comment, Long>, AdminCommentRepositoryCustom {

}
