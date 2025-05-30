package com.jobPrize.service.company.memberPool;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolDetailDto;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public interface MemberPoolService {
	
    Page<MemberPoolSummaryDto> readMemberPoolPageByCondition(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, MemberFilterCondition memberFilterCondition, Pageable pageable);
    MemberPoolDetailDto readMemberPoolDetail(UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed,Long memberId);
}