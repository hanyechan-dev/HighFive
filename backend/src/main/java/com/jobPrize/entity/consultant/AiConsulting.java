package com.jobPrize.entity.consultant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jobPrize.entity.memToCon.Request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ai_consulting")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiConsulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_consulting_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private Request request;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommonEnum.ConsultingType type;

    @Builder.Default
	@Column(nullable = false, name = "IS_SUBSCRIBED")
	private boolean isRequested = false;
    
    @Column(name = "request_date", nullable = false)
    private LocalDate requestedDate;
    
    @OneToOne(mappedBy = "aiConsulting", fetch = FetchType.LAZY)
    private ConsultantConsulting consultantConsulting;
    
	@OneToMany(mappedBy = "aiConsulting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AiConsultingContent> aiConsultingContents = new ArrayList<>();
	
	public void request() {
		if(isRequested) {
			return;
		}
		isRequested = true;
		requestedDate = LocalDate.now();
	}

 
}
