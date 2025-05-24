package com.jobPrize.service.company.memberPool;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;

public interface MemberPoolService {
	
    Page<MemberPoolSummaryDto> readMemberPoolPageByCondition(Long id, MemberFilterCondition memberFilterCondition, Pageable pageable);

}
