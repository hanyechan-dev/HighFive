package com.jobPrize.repository.company.advertisement;

import com.jobPrize.entity.company.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, AdvertisementRepositoryCustom {
	
}