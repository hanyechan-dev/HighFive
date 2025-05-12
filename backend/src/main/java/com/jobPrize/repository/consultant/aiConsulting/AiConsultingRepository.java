package com.jobPrize.repository.consultant.aiConsulting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.consultant.AiConsulting;

public interface AiConsultingRepository extends JpaRepository<AiConsulting, Long>, AiConsultingRepositoryCustom {

}
