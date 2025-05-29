package com.jobPrize.service.company.advertisement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.company.advertisement.AdvertisementCreateDto;
import com.jobPrize.dto.company.advertisement.AdvertisementResponeDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Advertisement;
import com.jobPrize.entity.company.Company;
import com.jobPrize.enumerate.ImageType;
import com.jobPrize.repository.company.advertisement.AdvertisementRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.FileUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

	private final CompanyRepository companyRepository;

	private final AdvertisementRepository advertisementRepository;

	private final AssertUtil assertUtil;
	
	private final FileUtil fileUtil;


	@Override
	public void createAdvertisement(Long id, UserType userType, AdvertisementCreateDto advertisementCreateDto) {

		assertUtil.assertUserType(userType, UserType.기업회원, "광고 등록");

		Company company = companyRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("기업"));

		MultipartFile file = advertisementCreateDto.getImage();

		String uuidName = fileUtil.saveImageAndGetUUIDName(file,ImageType.ADVERTISEMENT_IMAGE);
		
		if(uuidName==null) {
			throw new IllegalArgumentException("광고 이미지가 없습니다.");
		}

		Advertisement advertisement = Advertisement.builder().company(company).imageName(uuidName)
				.startDate(LocalDate.now()).endDate(LocalDate.now().plusMonths(1)).build();

		advertisementRepository.save(advertisement);
	}

	@Override
	@Transactional(readOnly = true)
	public AdvertisementResponeDto readAdvertisement(Long id) {

		Advertisement advertisements = advertisementRepository.findLatestByCompanyId(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("광고"));

		AdvertisementResponeDto advertisementResponeDto = AdvertisementResponeDto.builder().id(advertisements.getId())
				.imageUrl("/images/advertisement/" + advertisements.getImageName()).startDate(advertisements.getStartDate())
				.endDate(advertisements.getEndDate()).build();

		return advertisementResponeDto;

	}

}
