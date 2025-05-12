package com.jobPrize.repository.common02;

import java.util.List;

import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.QComment;
import com.jobPrize.entity.common.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminCommentRepositoryImpl implements AdminCommentRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public List<Comment> findCommentsByNickname(String nickname){
	
		QComment comment = QComment.comment;
		QUser user = QUser.user;
		
		return queryFactory
				.selectFrom(comment)
				.join(comment.user, user)
				.where(user.member.nickname.eq(nickname))
				.fetch();
				
	}
	
	

}
