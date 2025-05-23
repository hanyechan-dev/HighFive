package com.jobPrize.service.company.advertisement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.company.advertisement.AdvertisementCreateDto;
import com.jobPrize.dto.company.advertisement.AdvertisementResponeDto;
import com.jobPrize.entity.company.Advertisement;
import com.jobPrize.entity.company.Company;
import com.jobPrize.repository.company.advertisement.AdvertisementRepository;
import com.jobPrize.repository.company.company.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
	
	private final CompanyRepository companyRepository;
	
	private final AdvertisementRepository advertisementRepository;
	
	@Override
	public void createAdvertisement(Long id, AdvertisementCreateDto advertisementCreateDto) {
		
		Company company = companyRepository.findById(id)	
			.orElseThrow(()-> new EntityNotFoundException("존재 하지 않는 기업입니다."));
		
		Advertisement advertisement = Advertisement.of(company, advertisementCreateDto);
		
		advertisementRepository.save(advertisement);
			
	}

	@Override
	@Transactional(readOnly = true)
	public AdvertisementResponeDto readAdvertisement(Long id) {
		Advertisement advertisements = advertisementRepository.findLatestByCompanyId(id)
				.orElseThrow(()-> new EntityNotFoundException("해당 광고가 존재하지 않습니다."));

		AdvertisementResponeDto advertisementResponeDto = AdvertisementResponeDto.builder()
				.id(advertisements.getId())
				.imageUrl(advertisements.getImageUrl())
				.startDate(advertisements.getStartDate())
				.endDate(advertisements.getEndDate())
				.build();
		
		return advertisementResponeDto;
		
	}

}
