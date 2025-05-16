package com.jobPrize.repository.common.jobPosting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.QJobPosting;
import com.jobPrize.memberService.dto.jobPositing.JobPostingFilterCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	public Page<JobPosting> findAllByCondition(JobPostingFilterCondition condition, Pageable pageable){
		QJobPosting jobPosting = QJobPosting.jobPosting;
		
		List<JobPosting> results = queryFactory
				.selectFrom(jobPosting)
				.join(jobPosting.company)
				.where(
					jobPosting.careerType.like("%"+condition.getCareerType().toString()+"%"),
					jobPosting.educationLevel.eq(condition.getEducationLevel()),
					jobPosting.job.like("%"+condition.getJob().toString()+"%"),
					jobPosting.workLocation.like("%"+condition.getWorkLocation().toString()+"%"),
					jobPosting.salary.goe(condition.getSalary())
						)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		return new PageImpl<JobPosting>(results, pageable, countJobPostingByCondition(condition) );
	}
	
	public long countJobPostingByCondition(JobPostingFilterCondition condition) {
		QJobPosting jobPosting = QJobPosting.jobPosting;

	    return Optional.ofNullable(queryFactory
	        .select(jobPosting.count())
	        .from(jobPosting)
	        .where(
	        		jobPosting.careerType.like("%"+condition.getCareerType()+"%"),
					jobPosting.educationLevel.eq(condition.getEducationLevel()),
					jobPosting.job.like("%"+condition.getJob().toString()+"%"),
					jobPosting.workLocation.like("%"+condition.getWorkLocation().toString()+"%"),
					jobPosting.salary.goe(condition.getSalary())
						)
	        .fetchOne()).orElse(0L);
	}
}




