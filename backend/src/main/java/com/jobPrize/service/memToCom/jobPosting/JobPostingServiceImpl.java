package com.jobPrize.service.memToCom.jobPosting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryBySimilarityDto;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.repository.common.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

	private final JobPostingRepository jobPostingRepository;
	
	private final SimilarityRepository similarityRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<JobPostingSummaryDto> readJobPostingPageByCondition(JobPostingFilterCondition jobPostingFilterCondition, Pageable pageable) {
		Page<JobPosting> jobPostings = jobPostingRepository.findAllByCondition(jobPostingFilterCondition, pageable);
		List<JobPostingSummaryDto> jobPostingSummaryByConditionDtos = new ArrayList<>();
		for(JobPosting jobPosting : jobPostings) {
			JobPostingSummaryDto jobPostingSummaryByConditionDto = JobPostingSummaryDto.from(jobPosting);
			jobPostingSummaryByConditionDtos.add(jobPostingSummaryByConditionDto);
		}

		return new PageImpl<>(jobPostingSummaryByConditionDtos, pageable, jobPostings.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<JobPostingSummaryBySimilarityDto> readJobPostingPageBySimilarity(Long id, Pageable pageable) {
		Page<Similarity> similarities = similarityRepository.findAllWithJobPostingByMemberId(id, pageable);
		List<JobPostingSummaryBySimilarityDto> jobPostingSummaryBySimilarityDtos = new ArrayList<>();
		for(Similarity similarity : similarities) {
			JobPostingSummaryBySimilarityDto jobPostingSummaryBySimilarityDto = JobPostingSummaryBySimilarityDto.from(similarity);
			jobPostingSummaryBySimilarityDtos.add(jobPostingSummaryBySimilarityDto);
		}

		return new PageImpl<>(jobPostingSummaryBySimilarityDtos, pageable, similarities.getTotalElements());
	}



}
