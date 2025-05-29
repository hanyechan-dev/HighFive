package com.jobPrize.dto.company.advertisement;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdvertisementCreateDto {
	@NotNull(message = "이미지를 선택해주세요")
	private MultipartFile image;
}
