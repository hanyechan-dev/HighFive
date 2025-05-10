package com.jobPrize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@Column(name = "USER_ID")
	private Long id;

	@Column(nullable = false)
	private String nickname;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}

}
