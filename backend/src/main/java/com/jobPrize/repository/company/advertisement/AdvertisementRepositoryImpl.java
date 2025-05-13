package com.jobPrize.repository.company.advertisement;

import java.util.List;

import com.jobPrize.entity.company.Advertisement;
import com.jobPrize.entity.company.QAdvertisement;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdvertisementRepositoryImpl implements AdvertisementRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Advertisement> findAllByCompanyId(Long id) {
		QAdvertisement advertisement = QAdvertisement.advertisement;

		List<Advertisement> results = queryFactory
				.selectFrom(advertisement)
				.where(advertisement.company.id.eq(id))
				.fetch();

		
		return results;
	}
}
