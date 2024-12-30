package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_benefit")
public class Benefit {
    @EmbeddedId
    private BenefitId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_no", nullable = false, insertable = false, updatable = false) // 수정된 부분
    private JobPost jobPost;
}