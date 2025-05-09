package com.jobPrize.entity;

import com.jobPrize.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")			//관리자 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Admin {
	
	@Id
	@Column(name="USER_ID")
    private Long userId;		//관리자
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;

}
