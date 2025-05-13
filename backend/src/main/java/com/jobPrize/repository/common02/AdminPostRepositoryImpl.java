package com.jobPrize.repository.common02;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.QComment;
import com.jobPrize.entity.common.QPost;
import com.jobPrize.entity.common.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminPostRepositoryImpl implements AdminPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> findAllWithComments(Pageable pageable) {
        QPost post = QPost.post;
        QUser user = QUser.user;
        QComment comment = QComment.comment;

        
        // 게시글만 페이징
        List<Post> results = queryFactory
                .selectFrom(post)
                .join(post.user, user).fetchJoin() 
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

      
        return new PageImpl<>(results, pageable, countPosts());
    }

    public long countPosts() {
        QPost post = QPost.post;

        return queryFactory
                .select(post.count())
                .from(post)
                .fetchOne();
    }
}
