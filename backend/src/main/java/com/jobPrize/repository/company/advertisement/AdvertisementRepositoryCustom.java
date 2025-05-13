package com.jobPrize.repository.company.advertisement;

import java.util.List;

import com.jobPrize.entity.company.Advertisement;

public interface AdvertisementRepositoryCustom{
	List<Advertisement> findAllByCompanyId(Long id);
}