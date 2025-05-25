package com.jobPrize.service.company.jobPostingImage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.JobPostingImage;
import com.jobPrize.repository.company.jobPostingImage.JobPostingImageRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingImageServiceImpl implements JobPostingImageService {
	
    private final JobPostingImageRepository jobPostingImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void createImages(JobPosting jobPosting, List<MultipartFile> newFiles) {

        deleteImagesByJobPostingId(jobPosting.getId());

        for (MultipartFile file : newFiles) {
            if (file.isEmpty()) {
            	continue;
            } 

            String extension = "";
            String uuidName = "";
            int dotIndex = file.getOriginalFilename().lastIndexOf(".");
            if (dotIndex != -1) {
                extension = file.getOriginalFilename().substring(dotIndex);
            }
            uuidName = UUID.randomUUID() + extension;

            try {
                file.transferTo(new File(uploadDir + uuidName));
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }

            JobPostingImage jobPostingImage = JobPostingImage.builder()
                .imageName(uuidName)
                .jobPosting(jobPosting)
                .build();

            jobPostingImageRepository.save(jobPostingImage);
        }
    }

    private void deleteImagesByJobPostingId(Long jobPostingId) {
        List<JobPostingImage> images = jobPostingImageRepository.findAllByJobPostingId(jobPostingId);
        for (JobPostingImage image : images) {
            File file = new File(uploadDir + image.getImageName());
            if (file.exists()) file.delete();
        }
        jobPostingImageRepository.deleteAll(images);
    }

}
