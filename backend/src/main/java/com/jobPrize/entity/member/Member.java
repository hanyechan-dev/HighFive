package com.jobPrize.entity.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.memToCon.Request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

	@Id
	@Column(name="USER_ID", nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name="USER_ID", nullable = false)
	private User user;

	@Column(nullable = false)
	private String nickname;
	
	@Column(name = "last_update_time")
	LocalDateTime lastUpdateTime;

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Education> educations = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Career> careers = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Certification> certifications = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LanguageTest> languageTests = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CoverLetter> coverLetters = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CareerDescription> careerDescriptions = new ArrayList<>();
	

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Application> applications = new ArrayList<>();
    

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Similarity> similarities = new ArrayList<>();
    
	@OneToMany(mappedBy = "member")
	private List<Proposal> proposals = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<Request> requests = new ArrayList<>();
	

    public void updateNickname(String nickname) {
    	this.nickname=nickname;
    }
    
    public void changeLastUpdateTime() {
    	this.lastUpdateTime =LocalDateTime.now();
    }
    
    public static Member from(User user, String nickname) {
        return Member.builder()
            .user(user)
            .nickname(nickname)
            .build();
    }

}
