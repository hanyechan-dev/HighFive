package com.jobPrize.repository.company.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.QSimilarity;
import com.jobPrize.entity.member.Member;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyMemberRepositoryImpl implements CompanyMemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	@Override
	public Page<Member> findMembersByJobPostingIdOrderBySimilarityScore(Long id, Pageable pageable){
		QSimilarity similarity = QSimilarity.similarity;
		
		List<Member> results = queryFactory
			    .select(similarity.member) 
			    .from(similarity)
			    .where(similarity.jobPosting.id.eq(id))
			    .orderBy(similarity.score.desc()) 
			    .offset(pageable.getOffset()) 
			    .limit(pageable.getPageSize()) 
			    .fetch();
		

			return new PageImpl<Member>(results, pageable,countMembersByJobPostingId(id));
		}
	
	
	public long countMembersByJobPostingId(Long id) {
		QSimilarity similarity = QSimilarity.similarity;
		
		return Optional.ofNullable(
				queryFactory
				.select(similarity.member.count())
				.from(similarity)
				.where(similarity.jobPosting.id.eq(id))
				.fetchOne())
				.orElse(0L);
	}
	@Override
	public Optional<Member> findMemberById(Long id){
		QMember member = QMember.member;
		return Optional.ofNullable(
		        queryFactory
		            .selectFrom(member) 
		            .where(member.id.eq(id))
		            .fetchOne() 
		    );
		}
}



		


	

