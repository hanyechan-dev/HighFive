package com.jobPrize.service.company.memberPool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
import com.jobPrize.service.memToCom.util.MemToComUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPoolServiceImpl implements MemberPoolService {
	
	private final SimilarityRepository similarityRepository;
	
	private final MemToComUtil memToComUtil;

	@Override
	public Page<MemberPoolSummaryDto> readMemberPoolPageByCondition(Long id, MemberFilterCondition memberFilterCondition, Pageable pageable) {
		Page<Similarity> similarities = similarityRepository.findAllWithMemberByCompanyIdAndCondition(id, memberFilterCondition, pageable);
		List<MemberPoolSummaryDto> memberPoolSummaryDtos = new ArrayList<>();
		
		for(Similarity similarity : similarities) {
			boolean hasCareer = memToComUtil.hasCareer(similarity);
			String job = memToComUtil.job(similarity);
			EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(similarity);
			boolean isInterested = memToComUtil.isInterested(similarity);
			
			MemberPoolSummaryDto memberPoolSummaryDto = MemberPoolSummaryDto.of(similarity, hasCareer, job, latestEducationLevel, isInterested);
			
			memberPoolSummaryDtos.add(memberPoolSummaryDto);
		}
		return new PageImpl<MemberPoolSummaryDto>(memberPoolSummaryDtos,pageable,similarities.getTotalElements());
	}

}
