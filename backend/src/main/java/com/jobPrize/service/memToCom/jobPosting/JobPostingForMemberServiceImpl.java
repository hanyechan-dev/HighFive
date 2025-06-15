package com.jobPrize.service.memToCom.jobPosting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingForMemberResponseDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryForMemberDto;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.JobPostingImage;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.repository.company.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingForMemberServiceImpl implements JobPostingForMemberService {
	
	private final SimilarityRepository similarityRepository;
	
	private final JobPostingRepository jobPostingRepository;

	@Override
	public Page<JobPostingSummaryForMemberDto> readJobPostingPageByMemberIdAndCondition(Long id,JobPostingFilterCondition condition, Pageable pageable) {
		Page<Similarity> similarities = similarityRepository.findAllWithJobPostingByMemberIdAndCondition(id, condition, pageable);
		List<JobPostingSummaryForMemberDto> jobPostingSummaryForMemberDtos = new ArrayList<>();
		for(Similarity similarity : similarities) {
			JobPostingSummaryForMemberDto jobPostingSummaryForMemberDto = JobPostingSummaryForMemberDto.from(similarity);
			jobPostingSummaryForMemberDtos.add(jobPostingSummaryForMemberDto);
		}
		return new PageImpl<JobPostingSummaryForMemberDto>(jobPostingSummaryForMemberDtos,pageable,similarities.getTotalElements());
	}

	@Override
	public JobPostingForMemberResponseDto readJobPosting(Long jobPostingid) {
		JobPosting jobPosting = jobPostingRepository.findWithJobPostingImageByJobPostingId(jobPostingid)
				.orElseThrow(() -> new CustomEntityNotFoundException("공고"));
		
		List<JobPostingImage> jobPostingImages = jobPosting.getJobPostingImages();
		List<String> jobPostingImageUrls = new ArrayList<>();
		
		for(JobPostingImage jobPostingImage:jobPostingImages) {
			String jobPostingImageUrl = "/images/" + jobPostingImage.getImageName();
			jobPostingImageUrls.add(jobPostingImageUrl);
		}
		
		JobPostingForMemberResponseDto jobPostingForMemberResponseDto = JobPostingForMemberResponseDto.of(jobPosting, jobPostingImageUrls);
		return jobPostingForMemberResponseDto;
	}

}
