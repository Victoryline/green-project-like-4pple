package org.example.restserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_company_score")
public class CompanyScore {
    @EmbeddedId
    private CompanyScoreId id;

    @Column(name = "score", nullable = false)
    private Integer score;

}