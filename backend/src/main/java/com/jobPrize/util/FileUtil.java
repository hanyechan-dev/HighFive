package com.jobPrize.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.enumerate.ImageType;

@Component
public class FileUtil {
	
	@Value("${file.upload-jobposting-image-dir}")
	private String uploadJobPostingImageDir;
	
	@Value("${file.upload-advertisement-image-dir}")
	private String uploadAdvertisementImageDir;
	
	
	public String saveImageAndGetUUIDName(MultipartFile file, ImageType imageType) {
		
		String uploadDir="";
		
		
		if(ImageType.ADVERTISEMENT_IMAGE.equals(imageType)) {
			uploadDir=uploadAdvertisementImageDir;
		}
		else if(ImageType.JOBPOSTING_IMAGE.equals(imageType)) {
			uploadDir=uploadJobPostingImageDir;
		}
		else {
			throw new IllegalArgumentException("지원하지 않는 객체입니다.");
		}
		
		if (file == null || file.isEmpty()) {
			return null;
		}
			
		
		 String extension = "";
	     String uuidName = "";
	     int dotIndex = file.getOriginalFilename().lastIndexOf(".");
	     if (dotIndex != -1) {
	         extension = file.getOriginalFilename().substring(dotIndex);
	     }
	     uuidName = UUID.randomUUID() + extension;
	     
	     File dir = new File(uploadDir);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	     try {
	         file.transferTo(new File(uploadDir + uuidName));
	     } catch (IOException e) {
	         throw new RuntimeException("파일 저장 실패", e);
	     }
	     
	     return uuidName;
		
	}
	


}
