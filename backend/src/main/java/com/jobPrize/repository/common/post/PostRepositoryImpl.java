package com.jobPrize.repository.common.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.QComment;
import com.jobPrize.entity.common.QPost;
import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.member.QMember;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Map<String,Object> findAllWithCommentsCount(Pageable pageable) {
        QPost post = QPost.post;
        QUser user = QUser.user;
        QMember member = QMember.member;
        QComment comment = QComment.comment;

        
        // 게시글만 페이징
        List<Post> results = queryFactory
                .selectFrom(post)
                .join(post.user, user).fetchJoin()
                .join(user.member, member).fetchJoin()
                .orderBy(post.createdTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 댓글 수를 groupBy로 조회
        List<Long> postIds = results.stream()
                .map(Post::getId)
                .toList();

        List<Tuple> commentCounts = queryFactory
                .select(comment.post.id, comment.count())
                .from(comment)
                .where(comment.post.id.in(postIds))
                .groupBy(comment.post.id)
                .fetch();

        // Tuple에서 게시글 ID,댓글 수를 꺼내어 Map 형식으로 변환
        Map<Long, Long> commentCountMap = commentCounts.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(comment.post.id),
                        tuple -> tuple.get(comment.count())
                ));
        
        Map<String, Object> resultsAndcommentCountMap = new HashMap<>();
        
        resultsAndcommentCountMap.put("page", new PageImpl<>(results, pageable, countPosts()));
        resultsAndcommentCountMap.put("count", commentCountMap);

      
        return resultsAndcommentCountMap;
    }
    
	@Override
	public Optional<Post> findWithCommentsByPostId(Long id) {
		QPost post = QPost.post;
		QComment comment = QComment.comment;
		
		Post result = queryFactory
				.selectFrom(post)
				.leftJoin(post.comments, comment).fetchJoin()
				.where(post.id.eq(id))
				.distinct()
				.fetchOne();
				
		return Optional.ofNullable(result);
	}

    public long countPosts() {
        QPost post = QPost.post;

        return Optional.ofNullable(
        		queryFactory
                .select(post.count())
                .from(post)
                .fetchOne()
                ).orElse(0L);
    }



}
