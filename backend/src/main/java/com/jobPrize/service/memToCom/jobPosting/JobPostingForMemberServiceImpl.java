package com.jobPrize.service.memToCom.jobPosting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryForMemberDto;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingForMemberServiceImpl implements JobPostingForMemberService {
	
	private final SimilarityRepository similarityRepository;

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

}
