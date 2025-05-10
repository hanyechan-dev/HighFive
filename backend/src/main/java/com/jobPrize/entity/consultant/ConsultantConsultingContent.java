package com.jobPrize.entity.consultant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
@Table(name = "consultant_consulting_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultantConsultingContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultant_consulting_content_id")
    private Long id;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_consulting_id", nullable = false)
    private ConsultantConsulting consultantConsulting;

   
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private CommonEnum.DocumentType documentType;

 
    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private String content;
    
	public void updateContent(String item, String content) {
		this.item = item;
		this.content = content;
	}
    
}
