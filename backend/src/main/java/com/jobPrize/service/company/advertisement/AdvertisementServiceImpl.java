package com.jobPrize.service.company.advertisement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.company.advertisement.AdvertisementCreateDto;
import com.jobPrize.dto.company.advertisement.AdvertisementResponeDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Advertisement;
import com.jobPrize.entity.company.Company;
import com.jobPrize.repository.company.advertisement.AdvertisementRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
	
	private final CompanyRepository companyRepository;
	
	private final AdvertisementRepository advertisementRepository;
	
	private final AssertUtil assertUtil;
	
	@Override
	public void createAdvertisement(Long id, UserType usertype,AdvertisementCreateDto advertisementCreateDto) {
		assertUtil.assertUserType(usertype, UserType.기업회원, "광고 등록");
		Company company = companyRepository.findById(id)	
			.orElseThrow(()-> new CustomEntityNotFoundException("기업"));
		
		Advertisement advertisement = Advertisement.of(company, advertisementCreateDto);
		
		advertisementRepository.save(advertisement);
			
	}

	@Override
	@Transactional(readOnly = true)
	public AdvertisementResponeDto readAdvertisement(Long id) {
		Advertisement advertisements = advertisementRepository.findLatestByCompanyId(id)
				.orElseThrow(()-> new CustomEntityNotFoundException("광고"));

		AdvertisementResponeDto advertisementResponeDto = AdvertisementResponeDto.builder()
				.id(advertisements.getId())
				.imageUrl(advertisements.getImageUrl())
				.startDate(advertisements.getStartDate())
				.endDate(advertisements.getEndDate())
				.build();
		
		return advertisementResponeDto;
		
	}

}
