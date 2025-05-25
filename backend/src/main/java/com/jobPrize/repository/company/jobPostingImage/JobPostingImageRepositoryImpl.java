package com.jobPrize.repository.company.jobPostingImage;

import java.util.List;

import com.jobPrize.entity.company.JobPostingImage;
import com.jobPrize.entity.company.QJobPostingImage;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobPostingImageRepositoryImpl implements JobPostingImageRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<JobPostingImage> findAllByJobPostingId(Long Id) {
		QJobPostingImage jobPostingImage = QJobPostingImage.jobPostingImage;

		List<JobPostingImage> results = queryFactory
				.selectFrom(jobPostingImage)
				.join(jobPostingImage.jobPosting).fetchJoin()
				.where(jobPostingImage.jobPosting.id.eq(Id))
				.fetch();
		
		return results;
	}
}
