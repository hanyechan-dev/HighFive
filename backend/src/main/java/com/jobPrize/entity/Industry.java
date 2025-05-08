package com.jobPrize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "industry") // 테이블명: industry
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Industry {

    @Id
    @Column(name = "industry_code", length = 10) // 컬럼명: industry_code
    private String code;

    @Column(name = "industry_name", nullable = false, length = 50) // 컬럼명: industry_name
    private String name;

    @Column(name = "description", length = 200) // 컬럼명: description
    private String description;

    @OneToMany(mappedBy = "industry")
    private List<Company> companies;
}