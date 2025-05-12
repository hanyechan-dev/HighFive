package com.jobPrize.repository.consultant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.consultant.ConsultantConsulting;

public interface ConsultantConsultingRepository extends JpaRepository<ConsultantConsulting, Long>, ConsultantConsultingRepositoryCustom {

}
