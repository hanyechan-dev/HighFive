package com.jobPrize.dto.company.jobPostingImage;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class JobPostingImageCreateDto {
	
	@NotNull(message = "이미지를 선택해주세요.")
    private MultipartFile image;
}
