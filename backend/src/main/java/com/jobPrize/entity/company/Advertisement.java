package com.jobPrize.entity.company;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.dto.company.advertisement.AdvertisementCreateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@Table(name = "advertisement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "advertisement_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Company company;

	@Column(name = "image_url", nullable = false)
	private String imageName;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	public boolean isActivated() {
		return LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate);
	}
//	public static Advertisement of(Company company, AdvertisementCreateDto advertisementCreateDto) {
//		return Advertisement.builder()
//			.company(company)
//			.imageUrl(advertisementCreateDto.getImageUrl())
//			.startDate(LocalDate.now())
//			.endDate(LocalDate.now().plusMonths(1))
//			.build();
//	}

}