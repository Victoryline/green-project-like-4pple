package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_job_post_skill")
public class JobPostSkill {
    @EmbeddedId
    private JobPostSkillId id;

    @MapsId("jobPostNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_post_no", nullable = false)
    private JobPost jobPostNo;

}