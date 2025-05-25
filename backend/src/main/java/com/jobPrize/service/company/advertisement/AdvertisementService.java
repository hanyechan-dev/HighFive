package com.jobPrize.service.company.advertisement;

import com.jobPrize.dto.company.advertisement.AdvertisementCreateDto;
import com.jobPrize.dto.company.advertisement.AdvertisementResponeDto;
import com.jobPrize.entity.common.UserType;

public interface AdvertisementService {
	void createAdvertisement(Long id, UserType usertype, AdvertisementCreateDto advertisementCreateDto);
	AdvertisementResponeDto readAdvertisement(Long id);

}
