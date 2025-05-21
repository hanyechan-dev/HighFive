package com.jobPrize.entity.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.dto.common.user.signUp.UserSignUpDto;
import com.jobPrize.entity.admin.Admin;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.consultant.Consultant;
import com.jobPrize.entity.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	private String password;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, name = "BIRTH_DATE")
	private LocalDate birthDate;
	
	@Column(nullable = false, name = "GENDER_TYPE")
	@Enumerated(EnumType.STRING)
	private GenderType genderType;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@Builder.Default
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus = ApprovalStatus.WAITING;

	@Builder.Default
	@Column(nullable = false, name = "IS_SUBSCRIBED")
	private boolean isSubscribed = false;

	@CreatedDate
	@Column(nullable = false, name = "CREATED_DATE")
	private LocalDate createdDate;

	@Column(name = "DELETED_DATE")
	private LocalDate deletedDate;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Member member;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Company company;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Consultant consultant;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Admin admin;
	
	@OneToMany(mappedBy = "user")
	private List<Payment> payments = new ArrayList<>();
	
	@OneToMany(mappedBy = "user1")
	private List<ChatRoom> chatRoomsAsUser1 = new ArrayList<>();

	@OneToMany(mappedBy = "user2")
	private List<ChatRoom> chatRoomsAsUser2 = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<ChatContent> chatContents = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Subscription> Subscriptions = new ArrayList<>();

	
	
	public void updatePassword(String password) {
		this.password = password;
	}

	public void updatePhone(String phone) {
		this.phone = phone;
	}

	public void updateAddress(String address) {
		this.address = address;
	}

	public void deleteUser() {
		this.deletedDate = LocalDate.now();
	}

	public void subscribe() {
		this.isSubscribed = true;
	}

	public void unSubscribe() {
		this.isSubscribed = false;
	}
	
	public void approve() {
		this.approvalStatus = approvalStatus.APPROVED;
	}
	
	public void reject() {
		this.approvalStatus = approvalStatus.REJECTED;
	}

	public static User of(UserSignUpDto userSignUpDto, String encodedPassword) {
		return User.builder()
			.email(userSignUpDto.getEmail())
			.password(encodedPassword)
			.name(userSignUpDto.getName())
			.birthDate(userSignUpDto.getBirthDate())
			.genderType(userSignUpDto.getGenderType())
			.phone(userSignUpDto.getPhone())
			.address(userSignUpDto.getAddress())
			.type(userSignUpDto.getType())
			.build();
	}

}
