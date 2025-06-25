package com.jobPrize.service.company.jobPostingImage;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.JobPostingImage;
import com.jobPrize.enumerate.ImageType;
import com.jobPrize.repository.company.jobPostingImage.JobPostingImageRepository;
import com.jobPrize.util.FileUtil;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingImageServiceImpl implements JobPostingImageService {
	
    private final JobPostingImageRepository jobPostingImageRepository;
    
    private final FileUtil fileUtil;

    @Value("${file.upload-jobposting-image-dir}")
    private String uploadDir;
    

    @Override
    public void createImages(JobPosting jobPosting, List<MultipartFile> newFiles) {

        deleteImagesByJobPostingId(jobPosting.getId());

        for (MultipartFile file : newFiles) {
        	
    		String uuidName = fileUtil.saveImageAndGetUUIDName(file,ImageType.JOBPOSTING_IMAGE);
    		
    		if(uuidName==null) {
    			throw new CustomIllegalArgumentException("채용 공고 이미지가 없습니다.");
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
