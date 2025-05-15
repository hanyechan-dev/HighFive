package com.jobPrize.repository.memToCom.similarity;

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
public class SimilarityRepositoryImpl implements SimilarityRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Similarity> findAllWithJobPostingByMemberId(Long id, Pageable pageable) {
		QSimilarity similarity = QSimilarity.similarity;
		
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(similarity.member.id.eq(id))
			    .orderBy(similarity.score.desc())
			    .offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
			
		return new PageImpl<Similarity>(results, pageable, countSimilaritiesByMemberId(id));
	}
	

	@Override
	public Page<Similarity> findAllWithMemberByJobPostingId(Long id, Pageable pageable) {
		QSimilarity similarity = QSimilarity.similarity;
		
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(similarity.jobPosting.id.eq(id))
			    .orderBy(similarity.score.desc())
			    .offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
			
		return new PageImpl<Similarity>(results, pageable, countSimilaritiesByJobPostingId(id));
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
	
	public long countSimilaritiesByJobPostingId(Long id) {
	    QSimilarity similarity = QSimilarity.similarity;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(similarity.count())
	        .from(similarity)
	        .where(similarity.jobPosting.id.eq(id))
	        .fetchOne())
		    .orElse(0L);
	}

}
