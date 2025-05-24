package com.jobPrize.service.company.jobPosting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.company.jobPosting.JobPostingCreateDto;
import com.jobPrize.dto.company.jobPosting.JobPostingResponseDto;
import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;
import com.jobPrize.dto.company.jobPosting.JobPostingUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.company.jobPosting.JobPostingRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService{
	
	private final JobPostingRepository jobPostingRepository;

	private final CompanyRepository companyRepository;

	@Override
	public void createJobPosting(Long id, UserType userType, JobPostingCreateDto jobPostingCreateDto) {
		if(userType != UserType.기업회원){
			throw new AccessDeniedException("해당 기업 정보 조회 권한이 없습니다.");
		}
		
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 기업입니다."));
		
		JobPosting jobPosting = JobPosting.of(company,jobPostingCreateDto);
		
		jobPostingRepository.save(jobPosting);
	}

	@Override
	public Page<JobPostingSummaryDto> readJobPostingPage(Long id, Pageable pageable) {
		Page<JobPosting> jobPostings = jobPostingRepository.findAllByCompanyId(id, pageable);
		List<JobPostingSummaryDto> jobPostingSummaries = new ArrayList<>();
		for(JobPosting jobPosting : jobPostings) {
			JobPostingSummaryDto jobPostingSummaryDto = JobPostingSummaryDto.from(jobPosting);
			jobPostingSummaries.add(jobPostingSummaryDto);
		}
		return new PageImpl<>(jobPostingSummaries, pageable, jobPostings.getTotalElements());
	}

	@Override
	public JobPostingResponseDto readJobPosting(Long jobPostingId) {
		JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
		return JobPostingResponseDto.from(jobPosting);
	}

	@Override
	public void updateJobPosting(Long id, JobPostingUpdateDto jobPostingUpdateDto) {
		Long jobPostingId = jobPostingUpdateDto.getId();
		
		JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
		
		if(!jobPosting.getCompany().getId().equals(id)) {
			throw new AccessDeniedException("해당 공고 수정 권한이 없습니다.");
		}
		jobPosting.updateJobPostingInfo(jobPostingUpdateDto);
	}

	@Override
	public void deleteJobPosting(Long id, Long jobPostingId) {
		JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채용공고입니다."));
		
		if(!jobPosting.getCompany().getId().equals(id)) {
			throw new AccessDeniedException("해당 공고 삭제 권한이 없습니다.");
		}
		jobPostingRepository.delete(jobPosting);
	}

}
