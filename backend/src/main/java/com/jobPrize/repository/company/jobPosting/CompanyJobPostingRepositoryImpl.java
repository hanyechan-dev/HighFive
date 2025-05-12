package com.jobPrize.repository.company.jobPosting;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.QJobPosting;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyJobPostingRepositoryImpl implements CompanyJobPostingRepositoryCustom  {

		private final JPAQueryFactory queryFactory;

		@Override
		public List<JobPosting> findAllByCompanyId(Long id) {
			QJobPosting jobPosting= QJobPosting.jobPosting;
			

			List<JobPosting> results = queryFactory
					.selectFrom(jobPosting)
					.where(jobPosting.company.id.eq(id))
					.fetch();

			
			return results;
		}
		@Override
	    public Optional<JobPosting> findByCompanyId(Long id) { 
	        QJobPosting jobPosting = QJobPosting.jobPosting;

	        return Optional.ofNullable(queryFactory.selectFrom(jobPosting)
	            .where(jobPosting.id.eq(id))
	            .fetchOne()); // 
	    }
	}

	

