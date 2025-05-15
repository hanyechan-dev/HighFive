package com.jobPrize.repository.member.similarity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.company.QJobPosting;
import com.jobPrize.entity.memToCom.QSimilarity;
import com.jobPrize.entity.memToCom.Similarity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberSimilarityRepositoryImpl implements MemberSimilarityRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Similarity> findAllByMemberId(Long id, Pageable pageable) {
		QSimilarity similarity = QSimilarity.similarity;
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting, jobPosting).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(similarity.member.id.eq(id))
			    .orderBy(similarity.score.desc())
			    .offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
			
		return new PageImpl<Similarity>(results, pageable, countSimilaritiesByMemberId(id));
	}
	
	public long countSimilaritiesByMemberId(Long id) {
	    QSimilarity similarity = QSimilarity.similarity;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(similarity.count())
	        .from(similarity)
	        .where(similarity.member.id.eq(id))
	        .fetchOne())
		    .orElse(0L);
	}

}
