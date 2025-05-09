package com.jobPrize.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	@Column(name="USER_ID")
    private Long userId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;
	
    @Column(nullable = false)
	private String nickname;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Resume resume;
	
	@OneToMany(mappedBy = "member")
	private List<CoverLetter> coverLetters;
	
	@OneToMany(mappedBy = "member")
	private List<CareerDescription> careerDescriptions;
	
	@OneToMany(mappedBy = "member")
	private List<Application> applications;
//	
//	@OneToMany(mappedBy = "member")
//	private List<Interest> interests;
//	
//	@OneToMany(mappedBy = "member")
//	private List<Similarity> similarities;
//	
//	@OneToMany(mappedBy = "member")
//	private List<Proposal> proposals;
	

    public void updateNickname(String nickname) {
    	this.nickname=nickname;
    }

}
