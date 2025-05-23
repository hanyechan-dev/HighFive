package com.jobPrize.service.company.advertisement;

import com.jobPrize.dto.company.advertisement.AdvertisementCreateDto;
import com.jobPrize.dto.company.advertisement.AdvertisementResponeDto;

public interface AdvertisementService {
	void createAdvertisement(Long id, AdvertisementCreateDto advertisementCreateDto);
	AdvertisementResponeDto readAdvertisement(Long id);

}
