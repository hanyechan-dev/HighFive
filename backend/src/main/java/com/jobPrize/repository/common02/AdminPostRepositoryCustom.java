package com.jobPrize.repository.common02;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface AdminPostRepositoryCustom {
	Map<String,Object> findAllWithComments(Pageable pageable); 


}
