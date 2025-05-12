package com.jobPrize.repository.member.similarity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.company.QJobPosting;
import com.jobPrize.entity.memToCom.QSimilarity;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberSimilarityRepositoryImpl implements MemberSimilarityRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Similarity> findSimilaritysByMemberId(Long id, Pageable pageable) {
		QSimilarity similarity = QSimilarity.similarity;
		QMember member = QMember.member;
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting, jobPosting).fetchJoin()
			    .where(similarity.member.id.eq(id))
			    .orderBy(similarity.score.desc())
			    .offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
			
		return new PageImpl<Similarity>(results, pageable, countSimilaritysByMemberId(id));
	}
	
	public long countSimilaritysByMemberId(Long id) {
	    QSimilarity similarity = QSimilarity.similarity;

	    return queryFactory
	        .select(similarity.count())
	        .from(similarity)
	        .where(similarity.member.id.eq(id))
	        .fetchOne();
	}

}
