package com.jobPrize.repository.company.advertisement;

import java.util.List;
import java.util.Optional;

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
				.join(advertisement.company).fetchJoin()
				.where(advertisement.company.id.eq(id))
				.fetch();

		
		return results;
	}

	@Override
	public Optional<Advertisement> findLatestByCompanyId(Long id) {
		QAdvertisement advertisement = QAdvertisement.advertisement;

		Advertisement result = queryFactory
				.selectFrom(advertisement)
				.join(advertisement.company).fetchJoin()
				.where(advertisement.company.id.eq(id))
				.orderBy(advertisement.startDate.desc())
				.fetchOne();
		
		return Optional.ofNullable(result);
	}
}
