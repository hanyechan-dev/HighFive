package com.jobPrize.repository.memToCom.similarity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.entity.company.QJobPosting;
import com.jobPrize.entity.memToCom.QSimilarity;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.QMember;
import com.jobPrize.enumerate.EducationLevel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimilarityRepositoryImpl implements SimilarityRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Similarity> findAllWithJobPostingByMemberIdAndCondition(Long id, JobPostingFilterCondition condition, Pageable pageable) {
		QSimilarity similarity = QSimilarity.similarity;
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		BooleanBuilder builder = getBuilderFromJobPostingFilterCondition(condition);
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting, jobPosting).fetchJoin()
			    .join(jobPosting.company).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(
			    		similarity.member.id.eq(id),
			    		builder
			    		)
			    .orderBy(similarity.score.desc())
			    .offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
			
		return new PageImpl<Similarity>(results, pageable, countSimilaritiesByMemberIdAndCondition(id,condition));
	}
	
	@Override
	public List<Similarity> findFirst4WithJobPostingByMemberId(Long id) {
		QSimilarity similarity = QSimilarity.similarity;
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting, jobPosting).fetchJoin()
			    .join(jobPosting.company).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(similarity.member.id.eq(id))
			    .orderBy(similarity.score.desc())
			    .limit(4)
			    .fetch();
		
		
		// TODO Auto-generated method stub
		return results;
	}

	@Override
	public List<Similarity> findSecond8WithJobPostingByMemberIdAndCondition(Long id) {
		QSimilarity similarity = QSimilarity.similarity;
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting, jobPosting).fetchJoin()
			    .join(jobPosting.company).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(similarity.member.id.eq(id))
			    .orderBy(similarity.score.desc())
			    .offset(4)
			    .limit(8)
			    .fetch();
		
		return results;
	}
	
	
	
	
	@Override
	public Page<Similarity> findAllWithMemberByCompanyIdAndCondition(Long id, MemberFilterCondition condition, Pageable pageable) {
		
		
		QSimilarity similarity = QSimilarity.similarity;
		
		Long jobPostingId=latestJobPostingIdByCompanyId(id);
		
		
		
		BooleanBuilder builder = getBuilderFromMemberFilterCondition(condition);
		
		List<Similarity> results = queryFactory
			    .selectFrom(similarity)
			    .join(similarity.jobPosting).fetchJoin()
			    .join(similarity.member).fetchJoin()
			    .where(
			    		similarity.jobPosting.id.eq(jobPostingId),
			    		builder
			    		)
			    .orderBy(similarity.score.desc())
			    .offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
			
		return new PageImpl<Similarity>(results, pageable, countSimilaritiesByJobPostingIdAndCondition(jobPostingId,condition));
	}
	
	
	
	private long countSimilaritiesByMemberIdAndCondition(Long id, JobPostingFilterCondition condition) {
	    QSimilarity similarity = QSimilarity.similarity;
	    
	    BooleanBuilder builder = getBuilderFromJobPostingFilterCondition(condition);

	    return Optional.ofNullable(
	    	queryFactory
	        .select(similarity.count())
	        .from(similarity)
	        .where(
	        		similarity.member.id.eq(id),
	        		builder
	        		)
	        .fetchOne())
		    .orElse(0L);
	}
	
	private long countSimilaritiesByJobPostingIdAndCondition(Long id, MemberFilterCondition condition) {
	    QSimilarity similarity = QSimilarity.similarity;

	    BooleanBuilder builder = getBuilderFromMemberFilterCondition(condition);
	    
	    return Optional.ofNullable(
	    	queryFactory
	        .select(similarity.count())
	        .from(similarity)
	        .where(
	        		similarity.jobPosting.id.eq(id),
	        		builder
	        		)
	        .fetchOne())
		    .orElse(0L);
	}
	
	
	
	
	private BooleanBuilder getBuilderFromJobPostingFilterCondition(JobPostingFilterCondition condition) {
	    QJobPosting jobPosting = QJobPosting.jobPosting;
	    BooleanBuilder builder = new BooleanBuilder();

	    if (condition.getCareerType() != null ) {
	        builder.and(jobPosting.careerType.like("%" + condition.getCareerType().toString() + "%"));
	    }
	    if (condition.getEducationLevel() != null && !condition.getEducationLevel().isBlank()) {
	        builder.and(jobPosting.educationLevel.eq(EducationLevel.valueOf(condition.getEducationLevel())));
	    }
	    if (condition.getJob() != null) {
	        builder.and(jobPosting.job.like("%" + condition.getJob().toString() + "%"));
	    }
	    if (condition.getWorkLocation() != null) {
	        builder.and(jobPosting.workLocation.like("%" + condition.getWorkLocation().toString() + "%"));
	    }
	    if (condition.getSalary() != 0) {
	        builder.and(jobPosting.salary.goe(condition.getSalary()));
	    }

	    return builder;
	}
	
	
	
	
	
	
	
	
	
	
	
	


	private BooleanBuilder getBuilderFromMemberFilterCondition(MemberFilterCondition condition) {
		
		QMember member = QMember.member;
		BooleanBuilder builder = new BooleanBuilder();

		if (condition.isHasCareer()) {
		    builder.and(member.careers.size().gt(0));
		}

	    if (condition.getEducationLevel() != null && !condition.getEducationLevel().isBlank()) {
	        builder.and(member.educations.any().educationLevel.eq(EducationLevel.valueOf(condition.getEducationLevel())));
	    }
	    if (condition.getAddress() != null && !condition.getAddress().isBlank()) {
	        builder.and(member.user.address.like("%" + condition.getAddress() + "%"));
	    }
	    if (condition.getJob() != null && !condition.getJob().isBlank()) {
	        builder.and(member.careers.any().job.like("%" + condition.getJob() + "%"));
	    }
	    
	    return builder;
		
	}
	
	
	
	private Long latestJobPostingIdByCompanyId(Long id) {
		
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		
		Long jobPostingId = queryFactory
				.select(jobPosting.id)
				.from(jobPosting)
				.orderBy(jobPosting.createdDate.desc())
				.where(jobPosting.company.id.eq(id))
				.fetchFirst();
		
		return Optional.ofNullable(jobPostingId).orElse(0L);
	}



}
