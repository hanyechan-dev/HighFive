package com.jobPrize.repository.company.advertisement;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.company.Advertisement;

public interface AdvertisementRepositoryCustom{
	List<Advertisement> findAllByCompanyId(Long id);
	Optional<Advertisement> findLatestByCompanyId(Long id);
}