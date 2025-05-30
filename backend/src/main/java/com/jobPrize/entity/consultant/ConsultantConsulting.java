package com.jobPrize.entity.consultant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.enumerate.ConsultingType;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "consultant_consulting")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ConsultantConsulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultant_consulting_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_consulting_id", nullable = false, unique = true)
    private AiConsulting aiConsulting;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsultingType type;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Consultant consultant;
    
    @CreatedDate  //컨설턴트 컨설팅 승인 일자 
    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "completed_date")  //컨설턴트 컨설팅 완료 일자
    private LocalDate completedDate;

    @OneToMany(mappedBy = "consultantConsulting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantConsultingContent> consultantConsultingContents = new ArrayList<>();

 
    public void complete() {
    	this.completedDate = LocalDate.now();
    }
 
}
