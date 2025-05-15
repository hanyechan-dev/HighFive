package com.jobPrize.repository.memToCon.request;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCon.Request;

public interface RequestRepository extends JpaRepository<Request, Long>, RequestRepositoryCustom {

}
