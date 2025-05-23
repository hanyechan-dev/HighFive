package com.jobPrize.dto.company.advertisement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdvertisementUpdateDto {
	
	@NotNull(message = "수정 시 id는 필수입니다.")
	private Long id;

	@NotBlank(message = "수정할 이미지는 필수입니다.")
	private String imageUrl;
}
