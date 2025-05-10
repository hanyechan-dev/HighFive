package com.jobPrize.entity.admin;

import com.jobPrize.entity.common.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")			//관리자 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Admin {
	
	@Id
	@Column(name="USER_ID")
    private Long id;		//관리자
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;

}
