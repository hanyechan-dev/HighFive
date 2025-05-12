package com.jobPrize.repository.member.request;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCon.Request;

public interface MemberRequestRepository extends JpaRepository<Request, Long> {

}
