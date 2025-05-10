package com.jobPrize.entity.consultant;

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
	private Long userId;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}

}