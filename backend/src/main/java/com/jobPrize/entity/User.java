package com.jobPrize.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    
    
    @Column(nullable = false,name="CREATED_DATE")
    private LocalDateTime createdDate;
    
    @Column(name="DELETED_DATE")
    private LocalDateTime deletedDate;
    
    @Column(nullable = false, name="IS_SUBSCRIBED")
    private boolean isSubscribed;
    
    @Column(nullable = false)
    private UserType type;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Member member;

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
