package com.jobPrize.entity.consultant;

import java.util.ArrayList;
import java.util.List;

import com.jobPrize.entity.common.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultant {

	@Id
	@Column(name = "USER_ID")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;
	
	@OneToMany(mappedBy = "consultant")
	private List<ConsultantConsulting> consultantConsultings = new ArrayList<>();


}