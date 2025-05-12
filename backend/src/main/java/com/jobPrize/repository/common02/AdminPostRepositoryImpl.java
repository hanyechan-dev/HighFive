package com.jobPrize.repository.common02;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.QComment;
import com.jobPrize.entity.common.QPost;
import com.jobPrize.entity.common.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminPostRepositoryImpl implements AdminPostRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Page<Post> findAllWithComments(Pageable pageable){
		QPost post = QPost.post;
		QUser user = QUser.user;
		QComment comment = QComment.comment;
		
		List<Post> results = queryFactory
				.selectFrom(post)
				.join(post.user, user).fetchJoin()
				.leftJoin(post.comments, comment).fetchJoin()		//댓글이 없는 게시글 조회
				.orderBy(post.createdTime.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.distinct()
				.fetch();
				
		return new PageImpl<Post>(results,pageable,countPosts());
				
				
				
	}

	public long countPosts() {
		QPost post = QPost.post;
		
		return queryFactory
				.select(post.count())
				.from(post)
				.fetchOne();
	}
}
