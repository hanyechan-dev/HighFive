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
    private Long id;

   
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") 
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}