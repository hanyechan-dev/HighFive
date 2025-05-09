package com.jobPrize.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private String address;
    
	@CreatedDate
	@Column(nullable = false, name="CREATED_DATE")
	private LocalDate createdDate;
    
    @Column(name="DELETED_DATE")
    private LocalDate deletedDate;
    
    @Builder.Default
    @Column(nullable = false, name="IS_SUBSCRIBED")
    private boolean isSubscribed = false;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;
    
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Member member;
//    
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Company company;
//    
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Consultant consultant;
//    
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Admin admin;

	public void updatePassword(String password) {
		this.password = password;
	}
	
	public void updatePhone(String phone) {
		this.phone = phone;
	}
	
	public void updateAddress(String address) {
		this.address = address;
	}

	@Override
	public String getUsername() {
		return email;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(()->"ROLE_" + type.name());
	}
	
	@Override
	public boolean isAccountNonExpired() {
	    return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}

	@Override
	public boolean isEnabled() {
	    return deletedDate == null; // 삭제된 유저는 비활성 처리
	}
	

}
