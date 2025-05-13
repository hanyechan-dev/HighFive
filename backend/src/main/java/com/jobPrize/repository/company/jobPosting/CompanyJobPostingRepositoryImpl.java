package com.jobPrize.repository.company.jobPosting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.QJobPosting;
import com.jobPrize.entity.company.QJobPostingImage;
import com.jobPrize.entity.memToCom.QAppDocument;
import com.jobPrize.entity.memToCom.QApplication;
import com.jobPrize.entity.memToCom.QPass;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyJobPostingRepositoryImpl implements CompanyJobPostingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<JobPosting> findAllByCompanyId(Long id, Pageable pageable) {
		QJobPosting jobPosting = QJobPosting.jobPosting;

		List<JobPosting> results = queryFactory
				.selectFrom(jobPosting)
				.where(jobPosting.company.id.eq(id))
				.orderBy(jobPosting.createdDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		return new PageImpl<JobPosting>(results, pageable, countJobPostingsByCompanyId(id));
	}
	
	public long countJobPostingsByCompanyId(Long id) {
		QJobPosting jobPosting = QJobPosting.jobPosting;

		return Optional.ofNullable(
				queryFactory
				.select(jobPosting.count())
				.from(jobPosting)
				.where(jobPosting.company.id.eq(id))
				.fetchOne())
				.orElse(0L);
	}
	


	@Override
	public  Optional<JobPosting> findWithJobPostingImageByJobPostingId(Long id) {
		QJobPosting jobPosting = QJobPosting.jobPosting;
		QJobPostingImage jobPostingImage = QJobPostingImage.jobPostingImage;
		
		JobPosting result = queryFactory
				.selectFrom(jobPosting)
				.leftJoin(jobPosting.jobPostingImages, jobPostingImage).fetchJoin()
				.where(jobPosting.id.eq(id))
				.distinct()
				.fetchOne();
				return Optional.ofNullable(result);
	}

	@Override
	public Optional<JobPosting> findwithApplicationsByJobPostingId(Long id){
		QJobPosting jobPosting = QJobPosting.jobPosting;
		QApplication application = QApplication.application;
		QPass pass = QPass.pass;
		QAppDocument appDocument = QAppDocument.appDocument;
		JobPosting result = queryFactory
				.selectFrom(jobPosting)
				.leftJoin(jobPosting.applications, application).fetchJoin()
				.leftJoin(application.pass, pass).fetchJoin()
				.join(application.appDocument ,appDocument).fetchJoin()
				.where(jobPosting.id.eq(id))
				.distinct()
				.fetchOne();
				return Optional.ofNullable(result); 
				
	}
}