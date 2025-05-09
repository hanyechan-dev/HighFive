package com.jobPrize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "proposal")

@NoArgsConstructor
@AllArgsConstructor

public class Proposal  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    private Long proposalId;

   
    @Column(name = "proposal_title", nullable = false)
    private  String proposalTitle;

    @Column(name = "proposal_content",nullable = false)
    private String proposalContent;

    @Column(name = "proposal_salary",nullable = false)
    private Integer proposalSalary;
    
    @Column(name = "proposal_job",nullable = false)
    private Integer proposalJob;

    @Column(name = "proposal_date", nullable = false)
    private LocalDateTime proposalDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "proposal_status", nullable = false)
    private String proposalStatus; 
    
    @ManyToOne // 제안을 보낸 기업과의 관계
    @JoinColumn(name = "user_id") // 명확한 컬럼명 사용
    private User company;

    @ManyToOne // 제안을 받은 일반 회원과의 관계
    @JoinColumn(name = "user_id")
    private User member;
}